package net.bbs;

public class BbsDTO {
	  private int bbsno;
	  private String wname;
	  private String subject;
	  private String content;
	  private String passwd;
	  private int readcnt;
	  private String regdt;
	  private int grpno;
	  private int indent;
	  private int ansnum;
	  private String ip;
	  private int comment;		//답변갯수
	  
	  //기본생성자, getter, setter, toString()
	  public BbsDTO() {}

	/**
	 * @return the bbsno
	 */
	public int getBbsno() {
		return bbsno;
	}

	/**
	 * @param bbsno the bbsno to set
	 */
	public void setBbsno(int bbsno) {
		this.bbsno = bbsno;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * @return the regdt
	 */
	public String getRegdt() {
		return regdt;
	}

	/**
	 * @param regdt the regdt to set
	 */
	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	/**
	 * @return the grpno
	 */
	public int getGrpno() {
		return grpno;
	}

	/**
	 * @param grpno the grpno to set
	 */
	public void setGrpno(int grpno) {
		this.grpno = grpno;
	}

	/**
	 * @return the indent
	 */
	public int getIndent() {
		return indent;
	}

	/**
	 * @param indent the indent to set
	 */
	public void setIndent(int indent) {
		this.indent = indent;
	}

	/**
	 * @return the ansnum
	 */
	public int getAnsnum() {
		return ansnum;
	}

	/**
	 * @param ansnum the ansnum to set
	 */
	public void setAnsnum(int ansnum) {
		this.ansnum = ansnum;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param string the ip to set
	 */
	public void setIp(String string) {
		this.ip = string;
	}

	/**
	 * @return the comment
	 */
	public int getComment() {
		return comment;
	}

	/**
	 * @param comment the comment to set
	 */
	public void setComment(int comment) {
		this.comment = comment;
	}
	
}//class end
