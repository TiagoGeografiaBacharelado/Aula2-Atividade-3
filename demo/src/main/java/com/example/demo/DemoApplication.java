package com.example.demo;

import com.example.demo.applications.LancheApplication;
import com.example.demo.entities.Lanche;
import com.example.demo.facade.LancheFacade;
import com.example.demo.repositories.LancheRepository;
import com.example.demo.services.LancheService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class DemoApplication {
    private static LancheRepository lancheRepository;
    private static LancheService lancheService;
    private static LancheApplication lancheApplication;
    private static LancheFacade lancheFacade;

    private static void injetarDependencias() {
        lancheRepository = new LancheRepository();
        lancheService = new LancheService();
        lancheApplication = new LancheApplication(lancheService, lancheRepository);
        lancheFacade = new LancheFacade(lancheApplication);
    }

    public static void main(String[] args) {
        injetarDependencias();

        int codigo = 1;
        boolean quit = false;

        while (!quit) {
            System.out.println("""
                    1 - Cadastrar lanche
                    2 - Listar lanches
                    3 - Comprar lanche
                    4 - Sair""");
            int op = Integer.parseInt(System.console().readLine());
            switch (op) {
                case 1 -> {
                    System.out.println("Nome do lanche:");
                    String nome = System.console().readLine();
                    System.out.println("Preço:");
                    double preco = Double.parseDouble(System.console().readLine());
                    System.out.println("URL da imagem:");
                    String img_url = System.console().readLine();

                    lancheFacade.cadastrar(new Lanche(codigo++, nome, preco, img_url));
                    System.out.println("Lanche cadastrado com sucesso\n");
                }
                case 2 -> {
                    System.out.println("--------------------------------------\n CÓDIGO\tNOME\t\tPRECO\n--------------------------------------");
                    List<Lanche> lanches = lancheFacade.buscar();
                    for (Lanche l : lanches) {
                        System.out.println(l.getCodigo() + "\t\t" + l.getNome() + "\t\t" + l.getPreco() + "\n");
                    }
                }
                case 3 -> {
                    System.out.println("Digite o código do lanche:");
                    int cod = Integer.parseInt(System.console().readLine());
                    System.out.println("Digite a quantidade:");
                    int qtd = Integer.parseInt(System.console().readLine());
                    Lanche lanche = lancheFacade.buscarPorCodigo(cod);

                    String total = String.format("%.2f", lancheFacade.calcularLanche(lanche, qtd));
                    System.out.println("Total: R$ " + total);
                }
                case 4 -> quit = true;
            }
        }
    }
}


