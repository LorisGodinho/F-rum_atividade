package Forum_Loris_Godinho;

import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;


@Entity
public class Soldado {
	@Id
	private String id;
	private String nomeGuerra;

	@Enumerated(EnumType.STRING)
	private Distincao distincao;

	public Soldado() {
		id = UUID.randomUUID().toString();
	}

	public Soldado(String nomeGuerra, Distincao distincao) {
		this();
		this.nomeGuerra = nomeGuerra;
		this.distincao = distincao;
	}
	
	public Soldado(String id, String nomeGuerra, Distincao distincao) {
        this(nomeGuerra, distincao);
        this.id = id;
	}

	public String getId() {
		return id;
	}
	
	public Distincao getDistincao() {
		return distincao;
	}
	
	public String getnomeGuerra(){
		return nomeGuerra;
	}

	@Override public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o.getClass().equals(this.getClass()))) {
			return false;
		}
		Soldado Soldado = (Soldado) o;
		return Objects.equals(id, Soldado.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

}
