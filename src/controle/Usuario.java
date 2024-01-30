/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import View.Gerador_tela;
import com.sun.jdi.PathSearchingVirtualMachine;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import javax.swing.JOptionPane;
import lombok.*;

@Getter
/**
 *
 * @author Lucas
 */
public class Usuario {

    private String usuario;
    private String origem = "C:\\DataAce";
    private String destino = "C:\\Users\\";
    ArrayList<String> valores = new ArrayList<>();

    public ArrayList lista() throws FileNotFoundException {

        File diretorio = new File(destino);
        if (diretorio.isDirectory()) {

            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {

                for (File i : arquivos) {
                    valores.add(i.getName());
                }

            }

        }
        return valores;
    }

    public void criarArquivo(String usu) throws IOException {
        Path dir = Paths.get(destino + usu + "\\AppData\\Local\\DataAce");
        Path diretorio_start = Paths.get(destino + usu + "\\AppData\\Local\\DataAce\\start");

        if (Files.isDirectory(dir)) {
            System.out.println("Diretório Existe Usuário :[ " + usu + " ]");

            File arquivo = new File(destino + usu + "\\AppData\\Local\\DataAce\\start/start.ini");
            File dir_start = new File(destino + usu + "\\AppData\\Local\\DataAce\\start");

            if (!Files.isDirectory(diretorio_start)) {
                System.out.println("diretório Start não exite\n"
                        + "criando diretório.......");
                dir_start.mkdir();

                Path arquivo_startExeC2 = Paths.get("C:\\DataAce\\start/start.exe");
                Path arquivo_startExe = Paths.get(destino + usu + "\\AppData\\Local\\DataAce\\start/start.exe");
                if (!Files.isRegularFile(arquivo_startExe)) {
                    try {
                        Files.copy(arquivo_startExeC2, arquivo_startExe);
                        System.out.println("trasferindo arquivo start.exe\n"
                                + "origem: " + "C:\\DataAce\\start/start.exe\n"
                                + "destino: " + destino + usu + "\\AppData\\Local\\DataAce\\start\n");

                    } catch (Exception e) {
                        System.out.println(e.getMessage() + " " + e.getCause());
                    }

                }

                criarArquivo(usu);

            }

            arquivo.delete();
            arquivo.createNewFile();
            FileReader ler = new FileReader(arquivo);
            System.out.println(destino + usu + "\\AppData\\Local\\DataAce\\start/start.ini");
            FileWriter write = new FileWriter(arquivo);
            BufferedWriter buffWrite = new BufferedWriter(write);
            buffWrite.write("[Application]\n"
                    + "LocalApp=C:\\DataAce\\AlphaERP.exe\n"
                    + "RemoteApp=\\\\IP\\dataace\\alphaERP\\AlphaERP.exe\n"
                    + "\n"
                    + "# para usuarios de servidor local - \n"
                    + "#Way = \\\\\\\\IP\\\\Atualizador_AlphaERP\\\\alphaERP\n"
                    + "#Origem = \\\\\\\\IP\\\\Atualizador_AlphaERP    \n"
                    + "  \n"
                    + "\n"
                    + "\n"
                    + "\n"
                    + "[Control]\n"
                    + "Access=1\n"
                    + "\n"
                    + "[Instalacao]\n"
                    + "#IPServidor=IP\n"
                    + "Porta=5432\n"
                    + "BancoDados=db\n"
                    + "\n"
                    + "[Backup]\n"
                    + "Porta=45255\n"
                    + "\n"
                    + "[Atualizar]\n"
                    + "1=<" + origem + ">*.exe<" + destino + usu + "\\AppData\\Local\\DataAce\\>\n"
                    + "2=<" + origem + "\\RAV>*.rav<" + destino + usu + "\\AppData\\Local\\DataAce\\RAV>\n"
                    + "3=<" + origem + ">*.dll<" + destino + usu + "\\AppData\\Local\\DataAce>\n"
                    + "4=<" + origem + "\\DLL>*.dll<" + destino + usu + "\\AppData\\Local\\DataAce\\DLL>\n"
                    + "5=<" + origem + "\\DLL\\lib>*.dll<" + destino + usu + "\\AppData\\Local\\DataAce\\DLL\\lib>\n"
                    + "6=<" + origem + "\\DLL\\lib\\10>*.dll<" + destino + usu + "\\AppData\\Local\\DataAce\\DLL\\lib\\10>\n"
                    + "7=<" + origem + ">conf.ini<" + destino + usu + "\\AppData\\Local\\DataAce>\n"
                    + "8=<" + origem + ">logo*.*<" + destino + usu + "\\AppData\\Local\\DataAce>\n"
                    + "9=<" + origem + ">jasperviewer.jar<" + destino + usu + "\\AppData\\Local\\DataAce>\n"
                    + "10=<" + origem + "\\jasperviewer_lib>*.jar<" + destino + usu + "\\AppData\\Local\\DataAce\\jasperviewer_lib>\n"
                    + "11=<" + origem + "\\relatorios>th*.*<" + destino + usu + "\\AppData\\Local\\DataAce\\relatorios>\n"
                    + "12=<" + origem + "\\Schemas>*.*<" + destino + usu + "\\AppData\\Local\\DataAce\\Schemas>\n"
                    + "13=<" + origem + ">*.fr3<" + destino + usu + "\\AppData\\Local\\DataAce>\n"
                    + "14=<" + origem + "\\relatorios>*.fr3<" + destino + usu + "\\AppData\\Local\\DataAce\\relatorios>");

            //write.close();
            buffWrite.close();

            BufferedReader lerfiler = new BufferedReader(ler);
            String linha = lerfiler.readLine();

            while (linha != null) {
                System.out.println(linha);
                linha = lerfiler.readLine();
            }

        } else {
            File arquivo = new File(destino + usu + "\\AppData\\Local\\DataAce");
            arquivo.mkdir();
            System.out.println("Criando a pasta DataAace Diretório: " + destino + usu + "\\AppData\\Local\\");

            File Arquivo_start = new File(destino + usu + "\\AppData\\Local\\DataAce\\start");
            System.out.println("Criando diretório start");
            Arquivo_start.mkdir();
            System.out.println(" pasta Start criada no Diretório: " + destino + usu + "\\AppData\\Local\\");
            criarArquivo(usu);

            Path arquivo_startExeC2 = Paths.get("C:\\DataAce\\start/start.exe");
            Path arquivo_startExe = Paths.get(destino + usu + "\\AppData\\Local\\DataAce\\start/start.exe");
            if (!Files.isRegularFile(arquivo_startExe)) {
                try {
                    Files.copy(arquivo_startExeC2, arquivo_startExe);
                    System.out.println("trasferindo arquivo start.exe\n"
                            + "origem: " + "C:\\DataAce\\start/start.exe\n"
                            + "destino: " + destino + usu + "\\AppData\\Local\\DataAce\\start\n");

                } catch (Exception e) {
                    System.out.println(e.getMessage() + " " + e.getCause());
                }

            }
        }

    }

    public void atalho_start(String usu) throws IOException {
        Path caminho_dir = Paths.get(destino + usu + "\\AppData\\Local\\DataAce");

        if (Files.isDirectory(caminho_dir)) {
            System.out.println("Diretório existe ");

            File arquivo_startExe = new File(destino + usu + "\\AppData\\Local\\DataAce\\start/start.exe");
            if (arquivo_startExe.exists()) {
                System.out.println("Executável existe");
                String caminho_origem = "C:\\Users\\" + usu + "\\AppData\\Local\\DataAce\\start/start.exe";
                String caminho_desktop = "C:\\Users\\" + usu + "\\Desktop";
                String caminho_destino = caminho_desktop + "\\AlphaERP.exe";
                Path link = Paths.get(caminho_destino);

                Files.delete(link);

                try {

                    Path alvo = Paths.get(caminho_origem);
                    Path link2 = Paths.get(caminho_destino);

                    Files.createSymbolicLink(link2, alvo);

                    System.out.println("Atalho criado com sucesso no caminho: " + link2.toString());
                    //JOptionPane.showMessageDialog(null, "Atalho criado na área de trabalho dos respectivos  usuários.");

                } catch (IOException e) {

                    System.out.println("Erro ao tentar criar atalho: " + e.getMessage() + "\nO erro pode ter ocorrido pela inexistência do diretório\nou\npor não ter executado a aplicação como Administrador.");
                    JOptionPane.showMessageDialog(null, e.getMessage() + "\nExecute a aplicação como administrador.");
                    System.exit(1);

                }

            } else {
                System.out.println("Arquivo Start.exe não existe");
            }

        }

    }

    public void listarUsuario() {
        Path usuariosPath = Paths.get("C:\\Users");

        try {
            Files.walkFileTree(usuariosPath, EnumSet.noneOf(FileVisitOption.class), 1, new UserVisitor());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static class UserVisitor extends java.nio.file.SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (Files.isDirectory(file)) {

                // Obtém o nome do usuário a partir do caminho do diretório
                String nomeUsuario = file.getFileName().toString();
                if (!nomeUsuario.startsWith("Default") && !nomeUsuario.startsWith("All Users") && !nomeUsuario.startsWith("Public") && !nomeUsuario.startsWith("Todos os Usuários") && !nomeUsuario.startsWith("Usuário Padrão")) {
                    System.out.println("\nCriando arquivo Start\n");
                    System.out.println(nomeUsuario);

                }
            }
            return FileVisitResult.CONTINUE;
        }

    }

    public FileVisitResult visitFileFailed(Path file, IOException exc) {
        return FileVisitResult.CONTINUE;
    }
}
