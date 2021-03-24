package desafiovalidar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class DesafioValidar {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        
        FileInputStream arquivo = new FileInputStream("prog.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(arquivo));
        FileWriter escreve = new FileWriter("check-prog.txt");
        String linha;

        //Lê as linhas do arquivo
        
        while ((linha = br.readLine()) != null)   {
                      
            if(verificaLinha(linha)){
                //System.out.println(linha + " - " + "OK");
                escreve.write(linha + " - " + "OK\n");
            }
                
            else{
                //System.out.println(linha + " - " + "Inválido");
                escreve.write(linha + " - " + "Inválido\n");
            }      
        }
        escreve.close();
    }
    
    public static boolean verificaLinha(String linha){
        Stack<Character> simboloAberto = new Stack<Character>();
        boolean flag = false;
        for(int i = 0; i < linha.length(); i++){
            if(linha.charAt(i) == '(' || linha.charAt(i) == '[' || linha.charAt(i) == '{'){
                simboloAberto.push(linha.charAt(i));
            }
            else {
                if(simboloAberto.isEmpty()){
                    flag = false;
                    break;
                }
                else if (linha.charAt(i) == ')' && simboloAberto.peek() != '(' || 
                    linha.charAt(i) == ']' && simboloAberto.peek() != '[' || 
                    linha.charAt(i) == '}' && simboloAberto.peek() != '{')
                {
                    flag = false;
                    break;
                }
                simboloAberto.pop();
                           
            }
            if(i == linha.length() - 1 && simboloAberto.isEmpty())
                flag = true;
        }
        return flag;
    }   
}
