from git import Repo, Commit
import os, os.path
import sys
import platform
import lizard


def main(ANALYZED_REPO, COMMIT_FILE_INFO_LOG):
    # Start of the main program flow
    repo = Repo(ANALYZED_REPO)
    assert not repo.bare
    all_commits = list(repo.iter_commits())
    repo.git.checkout(all_commits[0])

    with open(COMMIT_FILE_INFO_LOG,'r') as file_info:
        actual_commit = ""
        for line in file_info:
            commit_sha, status, old ,filepath = line.split("\n")[0].split(";")
            if commit_sha != actual_commit:
                actual_commit = commit_sha
                repo.git.checkout(actual_commit)
                i = lizard.analyze_file(ANALYZED_REPO+filepath)

                loc = i.__dict__['nloc']

                cyc = 0
                for f in i.function_list:
                    #print(f.__dict__['cyclomatic_complexity'])
                    cyc = cyc + f.__dict__['cyclomatic_complexity']
                mod = repo.commit(actual_commit).stats.total['lines']
                with open ("additional_info.log", "a") as f_i:
                    print(line.split("\n")[0]+";"+str(loc)+";"+str(mod)+";"+str(cyc))
                    print(line.split("\n")[0]+";"+str(loc)+";"+str(mod)+";"+str(cyc), file=f_i)

    repo.git.checkout(all_commits[0])

if __name__ == '__main__':

    print(len(sys.argv), "len")
    if len(sys.argv) == 3:
        main(sys.argv[1], sys.argv[2])

    else:
        print("Error number of args are wrong expected 3")
