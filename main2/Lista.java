package main2;

import java.util.Random;

/**
 *
 * @author erics
 */
public class Lista {

    public static void main(String[] args) {
        Lista lista = new Lista();
        
        Random rd = new Random();
        Integer i = null;
        
        Livro livro1 = new Livro(0, "Robson", "Jackson", 1);
        Livro livro2 = new Livro(0, "Teste", "MMA", 2);
        Livro livro3 = new Livro(0, "Jurinel", "Osvaldo", 3);
        Livro livro4 = new Livro(0, "Rodnalson", "Ribamar", 4);

        lista.inserir(livro1);
        lista.inserir(livro2);
        lista.inserir(livro3);
        lista.inserir(livro4);

        System.out.println("=== Sem remover ====");
        lista.exibir();

        System.out.println("=== Ao remover ====");
        if (lista.remover(2)) {
            System.out.println("Removido com sucesso!");
        } else {
            System.out.println("Não removido!");
        }
        lista.exibir();
    }

    private Elemento inicio = null, atual, aux;

    //método de instância
    public void inserir(Object objeto) {
        if (inicio == null) {
            inicio = new Elemento(objeto, null);
            aux = inicio;
        } else {
            atual = new Elemento(objeto, null);
            aux.setProx(atual);//prox=atual;
            aux = atual;
        }
    }

    public void exibir() {
        Elemento x = inicio;
        while (x != null) {
            System.out.println(x.getObjeto());
            x = x.getProx();
        }
    }

    /**
     * Pesquisa os objetos da lista ligada simples.
     *
     * @param id int
     * @return Carro
     */
    public Livro pesquisarId(int id) {
        Elemento x = inicio;
        Livro livro;
        while (x != null) {
            livro = (Livro) x.getObjeto();
            if (id == livro.getId()) {
                return livro;
            }
            x = x.getProx();
        }
        return null;
    }

    private Elemento[] pesquisarRemove(int id) {
        Elemento x = inicio, auxRem = null;
        Elemento[] v = {x, auxRem};
        Livro livro;
        while (x != null) {
            livro = (Livro) x.getObjeto();
            if (id == livro.getId()) {
                v[0] = x;
                v[1] = auxRem;
                return v;
            }
            auxRem = x;
            x = x.getProx();
        }
        return null;
    }

    public boolean remover(int id) {
        Elemento[] v = pesquisarRemove(id);
        if (v != null) {//Verifica se achou!
            Elemento x = v[0];
            Elemento auxRem = v[1];
            if (x == inicio) {//Verifica se é o primeiro elemento
                inicio = x.getProx();
                x.setProx(null);
            } else if (x == atual) {//Verifica se é o último
                atual = auxRem;
                aux = auxRem;
                auxRem.setProx(null);
            } else {//Verifica se um elemento qualquer
                auxRem.setProx(x.getProx());
                x.setProx(null);
            }
            return true;
        }
        return false;//Informa que não removeu
    }

    //inner class - Classe interna
    private class Elemento {

        private Object objeto;//Dados
        private Elemento prox;

        public Elemento(Object objeto, Elemento prox) {
            this.objeto = objeto;
            this.prox = prox;
        }

        public Object getObjeto() {
            return objeto;
        }

        public void setObjeto(Object objeto) {
            this.objeto = objeto;
        }

        public Elemento getProx() {
            return prox;
        }

        public void setProx(Elemento prox) {
            this.prox = prox;
        }

        @Override
        public String toString() {
            return "Elemento{" + "objeto=" + objeto + ", prox=" + prox + '}';
        }

    }

}
