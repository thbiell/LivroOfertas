package main;

import java.util.*;

public class LivroDeOfertas {

    static class Oferta {
        int posicao;
        double valor;
        int quantidade;

        Oferta(int posicao, double valor, int quantidade) {
            this.posicao = posicao;
            this.valor = valor;
            this.quantidade = quantidade;
        }

        @Override
        public String toString() {
            return posicao + "," + String.format("%.2f", valor) + "," + quantidade;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numNotificacoes = Integer.parseInt(scanner.nextLine());
        List<Oferta> livroDeOfertas = new ArrayList<>();

        for (int i = 0; i < numNotificacoes; i++) {
            String[] notificacao = scanner.nextLine().split(",");
            int posicao = Integer.parseInt(notificacao[0]);
            int acao = Integer.parseInt(notificacao[1]);
            double valor = Double.parseDouble(notificacao[2]);
            int quantidade = Integer.parseInt(notificacao[3]);

            processarNotificacao(livroDeOfertas, posicao, acao, valor, quantidade);
        }

        imprimirLivroDeOfertas(livroDeOfertas);
        scanner.close();
    }

    static void processarNotificacao(List<Oferta> livroDeOfertas, int posicao, int acao, double valor, int quantidade) {
        switch (acao) {
            case 0:
                inserirOferta(livroDeOfertas, posicao, valor, quantidade);
                break;
            case 1:
                modificarOferta(livroDeOfertas, posicao, valor, quantidade);
                break;
            case 2:
                removerOferta(livroDeOfertas, posicao);
                break;
            default:
                System.out.println("Ação inválida.");
        }
    }

    static void inserirOferta(List<Oferta> livroDeOfertas, int posicao, double valor, int quantidade) {
        boolean updated = false;
        for (Oferta oferta : livroDeOfertas) {
            if (oferta.posicao == posicao) {
                oferta.valor = valor;
                oferta.quantidade = quantidade;
                updated = true;
                break;
            }
        }
        if (!updated) {
            livroDeOfertas.add(new Oferta(posicao, valor, quantidade));
        }
        ordenarLivroDeOfertas(livroDeOfertas);
    }

    static void modificarOferta(List<Oferta> livroDeOfertas, int posicao, double valor, int quantidade) {
        for (Oferta oferta : livroDeOfertas) {
            if (oferta.posicao == posicao) {
                if (valor != 0)
                    oferta.valor = valor;
                if (quantidade != 0)
                    oferta.quantidade = quantidade;
                break;
            }
        }
        ordenarLivroDeOfertas(livroDeOfertas);
    }

    static void removerOferta(List<Oferta> livroDeOfertas, int posicao) {
        livroDeOfertas.removeIf(oferta -> oferta.posicao == posicao);
        renumerarPosicoes(livroDeOfertas);
        ordenarLivroDeOfertas(livroDeOfertas);
    }

    static void renumerarPosicoes(List<Oferta> livroDeOfertas) {
        for (int i = 0; i < livroDeOfertas.size(); i++) {
            livroDeOfertas.get(i).posicao = i + 1;
        }
    }

    static void ordenarLivroDeOfertas(List<Oferta> livroDeOfertas) {
        Collections.sort(livroDeOfertas, Comparator.comparingInt(o -> o.posicao));
    }

    static void imprimirLivroDeOfertas(List<Oferta> livroDeOfertas) {
        for (int i = 0; i < livroDeOfertas.size(); i++) {
            System.out.print(livroDeOfertas.get(i));
            if (i < livroDeOfertas.size() - 1) {
                System.out.print("\n");
            } else {
                System.out.print("\\\n");
            }
        }
    }
}
