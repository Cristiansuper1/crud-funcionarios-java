package crud_inicial;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author cristian.moreira
 */
public class Crud_inicial {

    static class Funcionario {

        String nome;
        String funcao;
        double salario;

        public Funcionario(String nome, String funcao, double salario) {
            this.nome = nome;
            this.funcao = funcao;
            this.salario = salario;
        }
    }

    static ArrayList<Funcionario> funcionarios = new ArrayList<>();

    static Map<String, String> usuarios = new HashMap<>();

    static Map<String, Boolean> permissoes = new HashMap<>();

    static void usuariosTeste() {
        usuarios.put("admin", "admin123");
        permissoes.put("admin", true);

        usuarios.put("zezinho", "zezinho123");
        permissoes.put("zezinho", false);

        usuarios.put("clarinha", "clarinha123");
        permissoes.put("clarinha", false);
    }

    static String autenticar() {
        while (true) {
            String usuario = JOptionPane.showInputDialog("=== LOGIN ABER ===\n\nInsira nome de usuário:\n\n");

            if (usuario == null) {
                return null;
            }

            if (usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Nome de usuário não pode ser vazio");
                continue;
            }

            String senha = JOptionPane.showInputDialog("Insira sua senha:\n\n");

            if (senha == null) {
                return null;
            }

            if (usuarios.containsKey(usuario) && usuarios.get(usuario).equals(senha)) {
                boolean isAdmin = permissoes.get(usuario);
                String tipoUsuario = isAdmin ? "Administrador" : "Usuário comum";
                JOptionPane.showMessageDialog(null,
                        "Login bem sucedido.\n\nBem-vindo, " + usuario
                        + "\nTipo: " + tipoUsuario);
                return usuario;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Usuário ou senha incorretos.\n\nTente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        usuariosTeste();
        String usuarioLogado = autenticar();
        if (usuarioLogado == null) {
            JOptionPane.showMessageDialog(null, "Usuario não autenticado.\n\nEncerando programa...");
            System.exit(0);
        }

        boolean ehAdmin = permissoes.get(usuarioLogado);

        while (true) {
            String menu;
            if (ehAdmin) {
                menu = "Programa de cadastro de funcionarios da ABER\n"
                        + "Usuário: " + usuarioLogado + " (Administrador)\n\n"
                        + "Escolha uma opção:\n"
                        + "1 - Cadastrar Funcionário\n"
                        + "2 - Listar funcionários existentes\n"
                        + "3 - Modificar funcionário\n"
                        + "4 - Excluir funcionário\n"
                        + "5 - Sair do Programa\n\n";
            } else {
                menu = "Programa de cadastro de funcionarios da ABER\n"
                        + "Usuário: " + usuarioLogado + " (Usuário comum)\n\n"
                        + "Escolha uma opção:\n"
                        + "2 - Listar funcionários existentes\n"
                        + "5 - Sair do Programa\n\n";
            }
            String opcao = JOptionPane.showInputDialog(menu);
            if (opcao == null) {
                break;
            }
            int escolha;
            try {
                escolha = Integer.parseInt(opcao.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Caractere inválido, digite um número");
                continue;
            }
            switch (escolha) {
                case 1:
                    boolean incrementaFuncionario = true;
                    while (incrementaFuncionario) {
                        String nomeFuncionario = JOptionPane.showInputDialog("Insira nome do funcionario\n\n");
                        if (nomeFuncionario == null || nomeFuncionario.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Campo não pode ser vazio");
                            break;
                        }
                        if (nomeFuncionario.trim().matches(".*\\d.*")) {
                            JOptionPane.showMessageDialog(null, "Nome do funcionário não pode conter números!");
                            break;
                        }
                        boolean existeNome = false;
                        for (Funcionario funcionario : funcionarios) {
                            if (funcionario.nome.equalsIgnoreCase(nomeFuncionario)) {
                                existeNome = true;
                                break;
                            }
                        }
                        if (existeNome) {
                            JOptionPane.showMessageDialog(null, "Já existe um funcionário com esse nome, escolha outro por gentileza");
                            break;
                        }

                        String funcaoFuncionario = JOptionPane.showInputDialog("Insira função do funcionário\n\n");
                        if (funcaoFuncionario == null || funcaoFuncionario.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Campo não pode ser vazio");
                            break;
                        }
                        String salarioFuncionario = JOptionPane.showInputDialog("Insira salário do funcionário\n\n");
                        if (salarioFuncionario == null || salarioFuncionario.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Campo não pode ser vazio");
                            break;
                        }
                        double salario = 1;
                        try {
                            salario = Double.parseDouble(salarioFuncionario.trim());
                            if (salario < 0) {
                                JOptionPane.showMessageDialog(null, "Campo não pode ser negativo, tente novamente");
                                break;
                            } else if (salario == 0) {
                                JOptionPane.showMessageDialog(null, "Salário não pode ser 0, tente novamente");
                                break;
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Salário inválido, tente novamente");
                            break;
                        }
                        funcionarios.add(new Funcionario(nomeFuncionario, funcaoFuncionario, salario));
                        int confirmacaoAdiciona = JOptionPane.showConfirmDialog(
                                null,
                                "Funcionário cadastrado com sucesso. Deseja adicionar outro?\n\n",
                                "Confirmação",
                                JOptionPane.YES_NO_OPTION
                        );
                        if (confirmacaoAdiciona == JOptionPane.NO_OPTION) {
                            incrementaFuncionario = false;
                        }
                        if (confirmacaoAdiciona == JOptionPane.CLOSED_OPTION) {
                            incrementaFuncionario = false;
                        }
                    }
                    break;
                case 2:
                    if (funcionarios.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum funcionário cadastrado");
                    } else {
                        String lista = "Funcionários cadastrados:\n";
                        int i = 1;
                        for (Funcionario funcionario : funcionarios) {
                            lista = lista + i + "º Nome: " + funcionario.nome + ", Função: " + funcionario.funcao + ", Salário: R$ " + funcionario.salario + "\n";
                            i++;
                        }
                        JOptionPane.showMessageDialog(null, lista);
                    }
                    break;
                case 3:
                    if (funcionarios.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum funcionário para modificar");
                    } else {
                        String nomeBuscaFuncionario = JOptionPane.showInputDialog("Insira nome do funcionário\n\n");
                        boolean encontrado = false;
                        for (Funcionario funcionario : funcionarios) {
                            if (funcionario.nome.equalsIgnoreCase(nomeBuscaFuncionario)) {
                                encontrado = true;
                                String opcaoModificar = JOptionPane.showInputDialog("Funcionário encontrado! Escolha o que mudar nesse funcionário\n\n"
                                        + "1 -  Nome\n2 - Função\n3 - Salário\n4 -  Sair\n\n");
                                if (opcaoModificar == null) {
                                    break;
                                }
                                int escolhaModificar;
                                try {
                                    escolhaModificar = Integer.parseInt(opcaoModificar.trim());
                                } catch (NumberFormatException e) {
                                    JOptionPane.showMessageDialog(null, "Caractere inválido, DIGITE UM NÚMERO");
                                    break;
                                }
                                if (escolhaModificar == 1) {
                                    String novoNome = JOptionPane.showInputDialog("Insira o novo nome do funcionário\n\n");
                                    if (novoNome != null && !novoNome.trim().isEmpty()) {
                                        funcionario.nome = novoNome;
                                        JOptionPane.showMessageDialog(null, "Nome alterado com sucesso");
                                    }
                                } else if (escolhaModificar == 2) {
                                    String novaFuncao = JOptionPane.showInputDialog("Insira nova função\n\n");
                                    if (novaFuncao != null && !novaFuncao.trim().isEmpty()) {
                                        funcionario.funcao = novaFuncao;
                                        JOptionPane.showMessageDialog(null, "Função alterada com sucesso");
                                    }
                                } else if (escolhaModificar == 3) {
                                    String novoSalario = JOptionPane.showInputDialog("Insira novo salário\n\n");
                                    double salarioAlteracao = funcionario.salario;
                                    try {
                                        salarioAlteracao = Double.parseDouble(novoSalario.trim());
                                        funcionario.salario = salarioAlteracao;
                                        JOptionPane.showMessageDialog(null, "Salário alterado com seucesso\n\n");
                                    } catch (NumberFormatException e) {
                                        JOptionPane.showMessageDialog(null, "Salário inválido, tente novamente");
                                    }
                                } else if (escolhaModificar == 4) {
                                    break;
                                } else {
                                    JOptionPane.showMessageDialog(null, "opção inválida :(\n\n");
                                }
                                break;
                            }

                        }
                        if (!encontrado) {
                            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                        }
                    }
                    break;
                case 4:
                    if (funcionarios.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum funcionário para excluir");
                    } else {
                        String nomeBuscaFuncionario = JOptionPane.showInputDialog("Insira nome do funcionário\n\n");
                        boolean encontrado = false;
                        for (Funcionario funcionario : funcionarios) {
                            if (funcionario.nome.equalsIgnoreCase(nomeBuscaFuncionario)) {
                                encontrado = true;
                                int confirmacaoExcluir = JOptionPane.showConfirmDialog(null, "Funcionário encontrado! Deseja realmente excluí-lo?\n\n", "Confirmação", JOptionPane.YES_NO_OPTION);
                                if (confirmacaoExcluir == JOptionPane.YES_OPTION) {
                                    funcionarios.remove(funcionario);
                                    JOptionPane.showMessageDialog(null, "Funcionário excluído com sucesso");
                                } else {
                                    JOptionPane.showMessageDialog(null, "Exclusão cancelada");
                                }
                                break;
                            }
                        }
                        if (!encontrado) {
                            JOptionPane.showMessageDialog(null, "Funcionário não encontrado");
                        }
                    }
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Até mais");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente");
            }

        }
    }
}
