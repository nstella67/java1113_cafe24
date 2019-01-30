package net.pds;

public class PdsDTO {
	private int pdsno;
	private String wname;
	private String subject;
	private String regdate;
	private String passwd;
	private int readcnt;
	private String filename;
	private long filesize;
	private String ip;
	
	

	/**
	 * @return the pdsno
	 */
	public int getPdsno() {
		return pdsno;
	}



	/**
	 * @param pdsno the pdsno to set
	 */
	public void setPdsno(int pdsno) {
		this.pdsno = pdsno;
	}



	/**
	 * @return the wname
	 */
	public String getWname() {
		return wname;
	}



	/**
	 * @param wname the wname to set
	 */
	public void setWname(String wname) {
		this.wname = wname;
	}



	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}



	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}



	/**
	 * @return the regdate
	 */
	public String getRegdate() {
		return regdate;
	}



	/**
	 * @param regdate the regdate to set
	 */
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}



	/**
	 * @return the passwd
	 */
	public String getPasswd() {
		return passwd;
	}



	/**
	 * @param passwd the passwd to set
	 */
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}



	/**
	 * @return the readcnt
	 */
	public int getReadcnt() {
		return readcnt;
	}



	/**
	 * @param readcnt the readcnt to set
	 */
	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}



	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}



	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}



	/**
	 * @return the filesize
	 */
	public long getFilesize() {
		return filesize;
	}



	/**
	 * @param filesize the filesize to set
	 */
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}



	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}



	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PdsDTO [pdsno=" + pdsno + ", wname=" + wname + ", subject=" + subject + ", regdate=" + regdate
				+ ", passwd=" + passwd + ", readcnt=" + readcnt + ", filename=" + filename + ", filesize=" + filesize
				+ ", ip=" + ip + "]";
	}	
	
}//class end

