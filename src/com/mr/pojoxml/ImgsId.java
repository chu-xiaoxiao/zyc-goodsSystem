package com.mr.pojoxml;

/**
 * ImgsId entity. @author MyEclipse Persistence Tools
 */

public class ImgsId implements java.io.Serializable {

	// Fields

	private Integer imgid;
	private String imgpath;
	private Long gid;

	// Constructors

	/** default constructor */
	public ImgsId() {
	}

	/** full constructor */
	public ImgsId(Integer imgid, String imgpath, Long gid) {
		this.imgid = imgid;
		this.imgpath = imgpath;
		this.gid = gid;
	}

	// Property accessors

	public Integer getImgid() {
		return this.imgid;
	}

	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}

	public String getImgpath() {
		return this.imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public Long getGid() {
		return this.gid;
	}

	public void setGid(Long gid) {
		this.gid = gid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ImgsId))
			return false;
		ImgsId castOther = (ImgsId) other;

		return ((this.getImgid() == castOther.getImgid()) || (this.getImgid() != null
				&& castOther.getImgid() != null && this.getImgid().equals(
				castOther.getImgid())))
				&& ((this.getImgpath() == castOther.getImgpath()) || (this
						.getImgpath() != null && castOther.getImgpath() != null && this
						.getImgpath().equals(castOther.getImgpath())))
				&& ((this.getGid() == castOther.getGid()) || (this.getGid() != null
						&& castOther.getGid() != null && this.getGid().equals(
						castOther.getGid())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getImgid() == null ? 0 : this.getImgid().hashCode());
		result = 37 * result
				+ (getImgpath() == null ? 0 : this.getImgpath().hashCode());
		result = 37 * result
				+ (getGid() == null ? 0 : this.getGid().hashCode());
		return result;
	}

}