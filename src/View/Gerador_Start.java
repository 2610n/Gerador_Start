/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package View;

import controle.Usuario;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class Gerador_Start { 

    /**   teste 
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
      
        Usuario usu = new Usuario();
        
        ArrayList<String> list = usu.lista();
        
        for(String i:list){
            if(!i.equals("All Users") && !i.equals("Default")&&!i.equals("Default User")&&!i.equals("desktop.ini")
                    && !i.equals("Todos os Usuários")&& !i.equals("Usuário Padrão") && !i.equals("Public") )
            {
                 System.out.println(i);
                 usu.atalho_start(i);
            }
           
           
        }
       
        
           
   
        
    }
    
}
