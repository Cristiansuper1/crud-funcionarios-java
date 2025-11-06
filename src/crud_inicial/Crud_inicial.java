package crud_inicial;

import javax.swing.JOptionPane;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        while (true) {
            String opcao = JOptionPane.showInputDialog("Programa de cadastro de funcionarios da ABER\n\nEscolha uma opção:\n1 - Cadastrar Funcionário"
                    + "\n2 - Listar funcionários existentes\n3 - Modificar funcionário\n4 - Excluir funcionário\n"
                    + "5 - Sair do Programa\n\n");
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
                    String nomeFuncionario = JOptionPane.showInputDialog("Insira nome do funcionario\n\n");
                    if (nomeFuncionario == null || nomeFuncionario.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo não pode ser vazio");
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
                    }
                    String salarioFuncionario = JOptionPane.showInputDialog("Insira salário do funcionário\n\n");
                    if (salarioFuncionario == null || salarioFuncionario.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Campo não pode ser vazio");
                    }
                    double salario = 0;
                    try {
                        salario = Double.parseDouble(salarioFuncionario.trim());
                        if (salario < 0) {
                            JOptionPane.showMessageDialog(null, "Campo não pode ser negativo, tente novamente");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Salário inválido, tente novamente");
                        break;
                    }
                    funcionarios.add(new Funcionario(nomeFuncionario, funcaoFuncionario, salario));
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso.");
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
                                        + "1 -  Nome\n2 - Função\n3 - Salário\n\n");
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
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente");
            }

        }
    }

}
