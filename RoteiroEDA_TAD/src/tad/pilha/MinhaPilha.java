package tad.pilha;

public class MinhaPilha implements PilhaIF<Integer> {

	private int tamanho = 10;
	private Integer[] meusDados = null;
	private int topo = -1;

	public MinhaPilha(int tamanho) {
		this.tamanho = tamanho;
		meusDados = new Integer[tamanho];
	}

	public MinhaPilha() {
		meusDados = new Integer[tamanho];
	}

	@Override
public void empilhar(Integer item) throws PilhaCheiaException {
    if (topo == tamanho - 1) {
        throw new PilhaCheiaException();
    }
    
    topo = (topo + 1) % tamanho;
    meusDados[topo] = item;
}
	@Override
	public Integer desempilhar() throws PilhaVaziaException {
		if (isEmpty()) {
			throw new PilhaVaziaException("A pilha está vazia");
		}
		Integer itemDesempilhado = meusDados[topo];
		meusDados[topo] = null;
		topo--;
		return itemDesempilhado;
	}

	@Override
	public Integer topo() {
		if (isEmpty()) {
			return null;
		}
		return meusDados[topo];
	}
	@Override
	public PilhaIF<Integer> multitop(int k) {
		int posicao = -1;
		for (int i = 0; i <= topo; i++) {
			if (meusDados[i] == k) {
				posicao = i;
				break;
			}
		}
		
		if (posicao == -1) {
			throw new IllegalArgumentException("Valor não encontrado na pilha");
		}
		
		int elementosRestantes = topo - posicao + 1;
		PilhaIF<Integer> novaPilha = new MinhaPilha(elementosRestantes);
		int contador = 0;
		for (int i = posicao; i <= topo && contador < elementosRestantes; i++) {
			try {
				novaPilha.empilhar(meusDados[i]);
			} catch (PilhaCheiaException e) {
				// Tratamento opcional para exceção de pilha cheia
			}
			contador++;
		}
		return novaPilha;
	}

	@Override
	public boolean isEmpty() {
		if( topo == -1){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		MinhaPilha outraPilha = (MinhaPilha) obj;
		if (topo != outraPilha.topo) {
			return false;
		}
		for (int i = 0; i <= topo; i++) {
			if (!meusDados[i].equals(outraPilha.meusDados[i])) {
				return false;
			}
		}
		return true;
	}

}