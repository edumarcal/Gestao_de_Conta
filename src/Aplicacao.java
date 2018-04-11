import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Aplicacao {

	private static Scanner leitor;
	private static ListaContas lista = new ListaContas();

	public static void main(String[] args) {
		
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date(), "descricao", 1.0, ESituacao.A_PAGAR));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date(), "descricao", 1.0, ESituacao.EM_ATRASO));
		lista.adicionarConta(new Conta(EMes.JANEIRO, new Date(), "descricao", 1.0, ESituacao.PAGA));
		
		notificarContas();
		int opcao;
		do {
			leitor = new Scanner(System.in);
			System.out.println("-----------------------------------------------------");
			System.out.println("O que deseja fazer:");
			System.out.println("1 - Adicionar");
			System.out.println("2 - Alterar");
			System.out.println("3 - Remover");
			System.out.println("4 - Exibir");
			System.out.println("0 - Sair");
			System.out.println("-----------------------------------------------------");
			opcao = leitor.nextInt();
			switch (opcao) {
			case 0:
				System.out.println("Obrigado!");
				System.exit(0); // Finaliza a execução do programa
			case 1:
				System.out.println("Criar uma conta");
				lista.adicionarConta(criarConta());
				break;
			case 2:
				System.out.println("Alterar uma conta");
				lista.alterarConta(alterarConta(pegarConta()));
				break;
			case 3:
				System.out.println("Remover uma conta");
				lista.removerConta(pegarConta());
				break;
			case 4:
				listarTodos();
				break;
			}
		} while (opcao > 0 || opcao < 4);

	}

	@SuppressWarnings("deprecation")
	public static void notificarContas() {
		Date atual = new Date(); // data atual
		Notificacao notificar = new Notificacao();
		Date intervalo = new Date();
		DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");			
		try {
			intervalo = dt.parse((atual.getDay()+5+"/"+atual.getMonth()+"/"+atual.getYear()));
		} catch (ParseException e) {
			System.err.println("Erro na data");
		}
		if(lista.tamanho()!= 0) {
			System.out.println("Você tem conta pendente!");
			for (Conta c : notificar.pesquisarPorIntervalo(ESituacao.A_PAGAR, atual, intervalo,  lista)) {
				System.out.println(c);
			}
			for (Conta c : notificar.pesquisarPorIntervalo(ESituacao.EM_ATRASO, atual, intervalo,  lista)) {
				System.out.println(c);
			}
		}
		else {
			System.out.println("Parabéns você está em dia!");
		}	
	}

	public static Conta criarConta() {
		// atributos da conta
		EMes mes = addMes();
		Date data = addData();
		String descricao = addDescricao();
		Double valor = addValor();
		ESituacao situacao = addSituacao();

		return new Conta(mes, data, descricao, valor, situacao);

	}

	public static Conta alterarConta(Conta conta) {
		System.out.println("-----------------------------------------------------");
		System.out.println("Escolha uma das opções abaixo, para alterar o valor:");
		System.out.println("1 - Mês");
		System.out.println("2 - Data");
		System.out.println("3 - Descrição");
		System.out.println("4 - Valor");
		System.out.println("5 - Situação");
		System.out.println("6 - todos");
		System.out.println("-----------------------------------------------------");
		leitor = new Scanner(System.in);
		int opcao;
		Conta temp = conta; // variavel temporaria para guardar o valor a
							// ser alterado
		do {
			opcao = leitor.nextInt();
			switch (opcao) {
			case 1:
				temp.setMes(addMes());
				break;
			case 2:
				temp.setData(addData());
				break;
			case 3:
				temp.setDescricao(addDescricao());
				break;
			case 4:
				temp.setValor(addValor());
				break;
			case 5:
				temp.setSituacao(addSituacao());
				break;
			case 6:
				temp = criarConta();
				break;
			}
		} while (opcao < 1 || opcao > 6);
		return temp;
	}

	public static void listarTodos() {
		System.out.println("-----------------------------------------------------");
		System.out.println("Deseja exibir por:");
		System.out.println("1 - Mês");
		System.out.println("2 - Data");
		System.out.println("3 - Descrição");
		System.out.println("4 - Valor");
		System.out.println("5 - Situação");
		System.out.println("6 - Até a Data");
		System.out.println("7 - Todos");
		System.out.println("0 - Sair [retorna para o menu]");
		System.out.println("-----------------------------------------------------");

		leitor = new Scanner(System.in);
		Notificacao notificar = new Notificacao();
		int opcao;

		do {
			opcao = leitor.nextInt();
			switch (opcao) {
			case 0:
				return;
			case 1:
				for (Conta c : notificar.pesquisarPorMes(addMes(), lista)) {
					System.out.println(c);
				}
				break;
			case 2:
				for (Conta c : notificar.pesquisarPorData(addData(), lista)) {
					System.out.println(c);
				}
				break;
			case 3:
				for (Conta c : notificar.pesquisarPorDescricao(addDescricao(), lista)) {
					System.out.println(c);
				}
				break;
			case 4:
				for (Conta c : notificar.pesquisarPorValor(addValor(), lista)) {
					System.out.println(c);
				}
				break;
			case 5:
				for (Conta c : notificar.pesquisarPorSituacao(addSituacao(), lista)) {
					System.out.println(c);
				}
				break;
			case 6:
				for (Conta c : notificar.pesquisarPorIntervalo(addSituacao(), new Date(), addData(), lista)) {
					System.out.println(c);
				}
				break;
			case 7:
				System.out.println(lista);
				break;
			}
			if (opcao < 0 || opcao > 7)
				System.err.println("Operação inválida, informe o número correto!");
		} while (opcao < 0 || opcao > 7);
	}

	private static Conta pegarConta() {
		leitor = new Scanner(System.in);
		System.out.println(lista);
		System.out.println("------------------------------------------------------------");
		System.out.println("Digite o numero correspondente a conta que se deseja alterar");
		System.out.println("------------------------------------------------------------");
		int id;
		do {
			id = leitor.nextInt();
			if (id > 0 && id <= lista.tamanho())
				if (id < 0 && id >= lista.tamanho())
					System.err.println("indice inválido, favor informe novamente!");
		} while (id < 0 && id >= lista.tamanho());

		return lista.getConta(id);
	}
	
	// metodos de cadastro
	private static EMes addMes() {
		leitor = new Scanner(System.in);
		System.out.println("Informe o mês[1-12]");
		int m;
		do {
			m = leitor.nextInt() - 1;
			switch (m) {
			case 0:
				return EMes.JANEIRO;
			case 1:
				return EMes.FEVEREIRO;
			case 2:
				return EMes.MARCO;
			case 3:
				return EMes.ABRIL;
			case 4:
				return EMes.MAIO;
			case 5:
				return EMes.JUNHO;
			case 6:
				return EMes.JULHO;
			case 7:
				return EMes.AGOSTO;
			case 8:
				return EMes.SETEMBRO;
			case 9:
				return EMes.OUTUBRO;
			case 10:
				return EMes.NOVEMBRO;
			case 11:
				return EMes.DEZEMBRO;
			}
			if (m < 0 || m > 11)
				System.err.println("Mês inválido, favor informe a mês correto");
		} while (m < 0 || m > 11);

		return EMes.JANEIRO; // valor padrão

	}

	private static Date addData() {
		leitor = new Scanner(System.in);
		Date data = new Date();
		try {
			System.out.println("Informe a data [dd/MM/yyyy]");
			String d = leitor.next();
			DateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
			data = dt.parse(d);
			leitor = new Scanner(System.in);
			return data;
		} catch (Exception e) {
			System.err.println("Formato inválido");
			return null;
		}

	}

	private static String addDescricao() {
		leitor = new Scanner(System.in);
		System.out.println("Informe o descrição");
		String descricao = leitor.nextLine();
		return descricao;

	}

	private static Double addValor() {
		leitor = new Scanner(System.in);
		Double valor;
		System.out.println("Informe o valor");
		do {
			valor = leitor.nextDouble();
			if (valor <= 0)
				System.err.println("Valor não pode ser negativo, favor informe novamente");
		} while (valor <= 0);
		return valor;
	}

	private static ESituacao addSituacao() {
		leitor = new Scanner(System.in);

		System.out.println("Informe a situacao[1-3]");
		int s;
		do {
			s = leitor.nextInt() - 1;
			if (s >= 0 && s <= 2) {
				switch (s) {
				case 0:
					return ESituacao.A_PAGAR;
				case 1:
					return ESituacao.PAGA;
				case 2:
					return ESituacao.EM_ATRASO;
				}
			}
			if (s < 0 || s > 2)
				System.err.println("Situação inválida, favor informe novamente");
		} while (s < 0 || s > 2);

		return ESituacao.A_PAGAR; // valor padrão
	}

}