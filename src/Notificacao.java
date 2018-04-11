import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Notificacao {
	
	public List<Conta> pesquisarPorMes(EMes mes, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
		for (Conta conta : lista.getListaConta()) {
			if(mes == conta.getMes())
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}
	
	public List<Conta> pesquisarPorData(Date data, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
		for (Conta conta : lista.getListaConta()) {
			if(data.equals(conta.getData()))
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}
	
	public List<Conta> pesquisarPorDescricao(String descricao, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
		for (Conta conta : lista.getListaConta()) {
			if(conta.getDescricao().contains(descricao))
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}

	public List<Conta> pesquisarPorValor(Double valor, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
		for (Conta conta : lista.getListaConta()) {
			if(conta.getValor()<=valor)
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}
	
	public List<Conta> pesquisarPorSituacao(ESituacao situacao, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
		for (Conta conta : lista.getListaConta()) {
			if(situacao == conta.getSituacao())
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}
	
	public List<Conta> pesquisarPorIntervalo(ESituacao situacao, Date dataInicial, Date dataFinal, ListaContas lista) {
		ArrayList<Conta> resultadoPesquisado = new ArrayList<Conta>();
/*	
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date(), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date("06/25/2016"), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date("06/21/2016"), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date("06/30/2016"), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date("07/01/2016"), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date("05/01/2016"), "descricao", 1.0, ESituacao.A_PAGAR));
*/		
		for (Conta conta : lista.getListaConta()) {
			if((conta.getData().after(dataInicial) && conta.getData().before(dataFinal))  ||
			   (conta.getData().equals(dataFinal) || conta.getData().equals(dataInicial)) &&
			   situacao == conta.getSituacao())
					resultadoPesquisado.add(conta);
		}
		return resultadoPesquisado;
	}
	
}