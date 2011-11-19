package ic.doc.cpp.student.server.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

// @EntityListeners(BaseEntityListener.class)
@MappedSuperclass
public class BaseEntity {

  @Version
  @Column(name = "version", nullable = true)
  protected Integer version;

  @Column(name = "create_by", length = 50, nullable = true)
  protected String createdBy;

  @Column(name = "create_time", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date createdTimestamp;

  @Column(name = "update_by", length = 50, nullable = true)
  protected String updatedBy;

  @Column(name = "update_time", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  protected Date updatedTimestamp;

  public Integer getVersion() {
    return version;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public Date getCreatedTimestamp() {
    return createdTimestamp;
  }

  public void setCreatedTimestamp(Date createdTimestamp) {
    this.createdTimestamp = createdTimestamp;
  }

  public String getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  public Date getUpdatedTimestamp() {
    return updatedTimestamp;
  }

  public void setUpdatedTimestamp(Date updatedTimestamp) {
    this.updatedTimestamp = updatedTimestamp;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Created By: ").append(createdBy).append(", ");
    sb.append("Created Timestamp: ").append(createdTimestamp).append(", ");
    sb.append("Updated By: ").append(updatedBy).append(", ");
    sb.append("Update Timestamp: ").append(updatedTimestamp).append(", ");
    sb.append("Version: ").append(version);

    return sb.toString();
  }
}
