package apresentacao;

import java.util.List;
import java.util.Scanner;

import persistencia.*;
import model.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class Principal {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcao = -1;
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        DespesaDAO despesaDAO = new DespesaDAO();
        MetasDAO metasDAO = new MetasDAO();

        Usuario usuarioLogado = null;

        String menuPrincipal = """
				0 - Sair
				1 - Login
				2 - Cadastre-se
				""";
        String menuUsuario = """
				1 - Registrar Despesa
				2 - Registrar Metas
				3 - Ver Despesas
				4 - Ver Metas
				5 - Atualizar Cadastro
				6 - Deletar Cadastro
				7 - Atualizar Despesa
				8 - Deletar Despesa
				9 - Atualizar Meta
				10 - Deletar Meta
				0 - Sair
				""";

        while (opcao != 0) {
            try {
                if (usuarioLogado == null) {
                    System.out.println(menuPrincipal);
                    opcao = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcao) {
                        case 1 -> {
                            System.out.println("Email:");
                            String email = teclado.nextLine();
                            System.out.println("Senha:");
                            String senha = teclado.nextLine();

                            usuarioLogado = usuarioDAO.buscarPorEmailESenha(email, senha);
                            if (usuarioLogado != null) {
                                System.out.println("Login realizado com sucesso! Bem-vindo, " + usuarioLogado.getNome());
                            } else {
                                System.out.println("Email ou senha incorretos. Tente novamente.");
                            }
                        }
                        case 2 -> {
                            Usuario novoUsuario = new Usuario();
                            System.out.println("Email:");
                            novoUsuario.setEmail(teclado.nextLine());
                            System.out.println("Senha:");
                            novoUsuario.setSenha(teclado.nextLine());
                            System.out.println("Nome:");
                            novoUsuario.setNome(teclado.nextLine());
                            novoUsuario.setDataCriacao(new Timestamp(System.currentTimeMillis())); // Para data de criação
                            // do usuário

                            usuarioDAO.adicionar(novoUsuario);
                            System.out.println("Cadastrado com sucesso!");
                        }
                        case 0 -> System.out.println("Até logo!");
                        default -> System.out.println("Opção inválida!");
                    }
                } else {
                    System.out.println(menuUsuario);
                    opcao = teclado.nextInt();
                    teclado.nextLine();

                    switch (opcao) {
                        case 1 -> { // Registrar despesa
                            Despesa novaDespesa = new Despesa();
                            System.out.println("Descrição da Despesa:");
                            novaDespesa.setDescricao(teclado.nextLine());
                            System.out.println("Valor da Despesa:");
                            novaDespesa.setValor(teclado.nextDouble());
                            teclado.nextLine();

                            System.out.println("""
								Categoria:
								1 - Alimentação
								2 - Transporte
								3 - Contas
								4 - Entretenimento
								""");
                            Categoria categoria = new Categoria();
                            categoria.setIdCategoria(teclado.nextInt());
                            teclado.nextLine();

                            novaDespesa.setCategoria(categoria);
                            novaDespesa.setDataDespesa(new Timestamp(System.currentTimeMillis())); // Para data da despesa
                            novaDespesa.setUsuario(usuarioLogado);

                            despesaDAO.adicionar(novaDespesa);
                            System.out.println("Despesa registrada com sucesso!");
                        }
                        case 2 -> { // Registrar meta
                            Metas novaMeta = new Metas();
                            System.out.println("Defina o limite:");
                            novaMeta.setLimite(teclado.nextDouble());
                            teclado.nextLine();

                            System.out.println("""
								Categoria:
								1 - Alimentação
								2 - Transporte
								3 - Contas
								4 - Entretenimento
								""");
                            Categoria categoria = new Categoria();
                            categoria.setIdCategoria(teclado.nextInt());
                            teclado.nextLine();

                            novaMeta.setCategoria(categoria);
                            novaMeta.setUsuario(usuarioLogado);
                            novaMeta.setDataCriacao(new Timestamp(System.currentTimeMillis()));
                            metasDAO.adicionar(novaMeta);

                            System.out.println("Meta registrada com sucesso!");
                        }
                        case 3 -> { // Ver despesas
                            List<Despesa> despesas = despesaDAO.buscarTodas();
                            if (despesas.isEmpty()) {
                                System.out.println("Nenhuma despesa registrada.");
                            } else {
                                System.out.println("Despesas Registradas:");
                                for (Despesa despesa : despesas) {
                                    System.out.println(despesa); // Usa o novo toString()
                                }
                            }
                        }

                        case 4 -> { // Ver metas
                            List<Metas> metas = metasDAO.buscarTodas();
                            if (metas.isEmpty()) {
                                System.out.println("Nenhuma meta registrada.");
                            } else {
                                System.out.println("Metas Registradas:");
                                for (Metas meta : metas) {
                                    System.out.println(meta); // Usa o novo toString()
                                }
                            }
                        }

                        case 5 -> { // Atualizar cadastro
                            System.out.println("Atualize seu nome:");
                            usuarioLogado.setNome(teclado.nextLine());
                            usuarioDAO.atualizar(usuarioLogado);
                            System.out.println("Cadastro atualizado!");
                        }
                        case 6 -> { // Deletar cadastro
                            usuarioDAO.excluir(usuarioLogado.getIdUsuario());
                            usuarioLogado = null;
                            System.out.println("Conta deletada com sucesso!");
                        }
                        case 7 -> { // Atualizar despesa
                            System.out.println("Informe o ID da despesa que deseja atualizar:");
                            int idDespesa = teclado.nextInt();
                            teclado.nextLine();
                            Despesa despesaAtualizar = despesaDAO.buscarPorId(idDespesa);

                            if (despesaAtualizar != null) {
                                System.out.println("Nova descrição:");
                                despesaAtualizar.setDescricao(teclado.nextLine());
                                System.out.println("Novo valor:");
                                despesaAtualizar.setValor(teclado.nextDouble());
                                teclado.nextLine();

                                despesaDAO.atualizar(despesaAtualizar);
                                System.out.println("Despesa atualizada com sucesso!");
                            } else {
                                System.out.println("Despesa não encontrada.");
                            }
                        }
                        case 8 -> { // Deletar despesa
                            System.out.println("Informe o ID da despesa que deseja deletar:");
                            int idDespesa = teclado.nextInt();
                            teclado.nextLine();
                            despesaDAO.excluir(idDespesa);
                            System.out.println("Despesa deletada com sucesso!");
                        }
                        case 9 -> { // Atualizar meta
                            System.out.println("Informe o ID da meta que deseja atualizar:");
                            int idMeta = teclado.nextInt();
                            teclado.nextLine();
                            Metas metaAtualizar = metasDAO.buscarPorId(idMeta);

                            if (metaAtualizar != null) {
                                System.out.println("Novo limite:");
                                metaAtualizar.setLimite(teclado.nextDouble());
                                teclado.nextLine();

                                metasDAO.atualizar(metaAtualizar);
                                System.out.println("Meta atualizada com sucesso!");
                            } else {
                                System.out.println("Meta não encontrada.");
                            }
                        }
                        case 10 -> {
                            System.out.println("Informe o ID da meta que deseja deletar:");
                            int idMeta = teclado.nextInt();
                            teclado.nextLine();
                            metasDAO.excluir(idMeta);
                            System.out.println("Meta deletada com sucesso!");
                        }
                        case 0 -> {
                            usuarioLogado = null;
                            System.out.println("Você foi desconectado.");
                        }
                        default -> System.out.println("Opção inválida!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
                teclado.nextLine();
            }
        }
        teclado.close();
    }
}
