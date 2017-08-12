package rs.odgajivacnica.admin.model;

import javax.persistence.*;


@MappedSuperclass
public class BaseEntity {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Column(name = "version")
	@Version
	private int version = 0;

	@Override
	public String toString() {
		return this.getClass().getName() + " [ID=" + id + "]";
	}
}
