import java.util.Date;

public class Conta {
	private int id;
	private EMes mes;
	private Date data;
	private String descricao;
	private Double valor;
	private ESituacao situacao;

	public Conta() {
	}

	public Conta(EMes mes, Date data, String descricao, Double valor, ESituacao situacao) {
		this.mes = mes;
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.situacao = situacao;
	}

	public EMes getMes() {
		return mes;
	}

	public void setMes(EMes mes) {
		this.mes = mes;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public ESituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		/*
		String resultadoFormatado = "Mês: " + this.mes + "\n" + "Data: " + this.data + "\n" + "Descrição: "
				+ this.descricao + "\n" + "Valor: R$ " + this.valor + "\n" + "Situação: " + this.situacao + "\n";
		return resultadoFormatado;
		*/
		String resultadoFormatado = "[" + id + " -> Mês: " + this.mes + ", " + "Data: " + this.data + ", "
				+ "Descrição: " + this.descricao + ", " + "Valor: R$ " + this.valor + ", " + "Situação: "
				+ this.situacao + "]";
		return resultadoFormatado;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
}