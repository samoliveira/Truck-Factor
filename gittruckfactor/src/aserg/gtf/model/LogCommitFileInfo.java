package aserg.gtf.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.eclipse.persistence.annotations.Index;

@Entity
public class LogCommitFileInfo extends AbstractEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	static int count = 0;
	private Integer tempId;
	
	private String repositoryName;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	private LogCommitInfo commitInfo;
	
	@Index(name="OLDFILENAMEINDEX")
	@Column(length = 1000)
	private String oldFileName;
	@Index(name="NEWFILENAMEINDEX")
	@Column(length = 1000)
	private String newFileName;
	@Enumerated(EnumType.STRING)
	private Status status;

	private Integer fileSize;
	private Integer modificationSize;
	private Integer ciclomaticComplexity;

	public Integer getFileSize() {
		return fileSize;
	}

	public void setFileSize(Integer fileSize) {
		this.fileSize = fileSize;
	}

	public Integer getModificationSize() {
		return modificationSize;
	}

	public void setModificationSize(Integer modificationSize) {
		this.modificationSize = modificationSize;
	}

	public Integer getCiclomaticComplexity() {
		return ciclomaticComplexity;
	}

	public void setCiclomaticComplexity(Integer ciclomaticComplexity) {
		this.ciclomaticComplexity = ciclomaticComplexity;
	}




	public LogCommitFileInfo() {
		// TODO Auto-generated constructor stub
	}


	
	
	public LogCommitFileInfo(LogCommitInfo commitInfo, String status, String oldFileName, String newFileName) {
		super();
		this.commitInfo = commitInfo;
		this.repositoryName = commitInfo.getRepositoryName();
		this.oldFileName = oldFileName;
		this.newFileName = newFileName;
		this.status = Status.getStatus(status);
		this.setTempId(count++);
	}


	public LogCommitFileInfo(LogCommitInfo commitInfo, String status, String oldFileName, String newFileName, Integer fileSize, Integer modificationSize, Integer ciclomaticComplexity) {
		super();
		this.commitInfo = commitInfo;
		this.repositoryName = commitInfo.getRepositoryName();
		this.oldFileName = oldFileName;
		this.newFileName = newFileName;

		this.status = Status.getStatus(status);

		this.setTempId(count++);

		this.ciclomaticComplexity=ciclomaticComplexity;
		this.modificationSize = modificationSize;
		this.fileSize = fileSize;

	}
	
	@Override
	public String toString() {
		return newFileName + ", " + status; //+ ", " + additions  + ", " + deletions + ", " + " + commitId + ", " + message; 
	}
	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String fileName) {
		this.newFileName = fileName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

//	@Override
//	public int compareTo(CommitFileInfo o) {
////		if (this.status == Status.ADDED)
////			if (o.status == Status.ADDED)
////				return 0;
////			else 
////				return -1;
//		return this.date.compareTo(o.date);
//	}
	

	public String getOldFileName() {
		return oldFileName;
	}

	public void setOldFileName(String oldFileName) {
		this.oldFileName = oldFileName;
	}


	public Long getId() {
		return id;
	}


	public String getRepositoryName() {
		return repositoryName;
	}


	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}


	public LogCommitInfo getCommitInfo() {
		return commitInfo;
	}


	public void setCommitInfo(LogCommitInfo commitInfo) {
		this.commitInfo = commitInfo;
	}




	public Integer getTempId() {
		return tempId;
	}




	public void setTempId(Integer tempId) {
		this.tempId = tempId;
	}


	

	


}
