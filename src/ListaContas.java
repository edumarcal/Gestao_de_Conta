import java.util.ArrayList;
import java.util.List;

public class ListaContas {
	private static int CONTAGEM = 0; 
	private List<Conta> listaContas;
	

	public ListaContas() {
		this.listaContas = new ArrayList<Conta>();
	}

	public int tamanho() {
		return listaContas.size();
	}
	public List<Conta> getListaConta(){
		return listaContas;
	}
	
	public void adicionarConta(Conta conta) {
		if (conta != null) {
			conta.setId(CONTAGEM++);
			listaContas.add(conta);
		}
			
	}

	public void removerConta(Conta conta) {
		if (conta != null)
			listaContas.remove(conta);
	}

	public Conta getConta(int id) {
		for (Conta c : listaContas) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}
	
	
	public void alterarConta(Conta contaVelha) {
		for (Conta c : listaContas) {
			if (c.equals(contaVelha)) {
				listaContas.set(c.getId(), contaVelha);				
			}				
		}

	}

	@Override
	public String toString() {
		String resultado = "";
		for (Conta conta : listaContas) {
			//resultado += conta.toString()+"\n";
			resultado += conta +"\n";
		}
		return resultado;
	}
}