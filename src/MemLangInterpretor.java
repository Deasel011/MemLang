import memlang.interpretor.Interpretor;
import memlang.syntax.lexer.Lexer;
import memlang.syntax.lexer.LexerException;
import memlang.syntax.node.Node;
import memlang.syntax.parser.Parser;
import memlang.syntax.parser.ParserException;

import java.io.*;
import java.util.Scanner;

/**
 * Created by pdesl on 2017-03-25.
 */
public class MemLangInterpretor {

    public static void main(String[] args){
        PushbackReader in = null;
        Scanner userInput = new Scanner(System.in);

        if (args.length == 0) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Interpretor runnable = new Interpretor();
            try {
                String s = br.readLine();
                while(s!=null){
                    in = new PushbackReader(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(s.getBytes()))), 1024);
                    Node syntaxTree = null;
                    try{
                        syntaxTree = new Parser(new Lexer(in)).parse();
                    } catch (ParserException e) {
                        System.out.println("Unknown command:"+s);
                    } catch (LexerException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    assert syntaxTree != null;
                    try {
                        syntaxTree.apply(runnable);
                    }catch(RuntimeException e){
                        e.printStackTrace();
                    }catch (java.lang.UnsatisfiedLinkError e){
                        System.out.println("Error, must be on Windows platform");
                    }catch (java.lang.NoClassDefFoundError e){
                        System.out.println("MemManip could not be loaded");
                    }
                    s = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        } else if (args.length == 1) {

            try {
                in = new PushbackReader(new FileReader(args[0]), 1024);
            }
            catch (FileNotFoundException e) {
                System.err.println(
                        "INPUT ERROR: file not found '" + args[0] + "'.");
                System.exit(1);
            }

            Node syntaxTree = null;
            try{
                syntaxTree = new Parser(new Lexer(in)).parse();
            } catch (ParserException e) {
                e.printStackTrace();
            } catch (LexerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert syntaxTree != null;
            try {
                syntaxTree.apply(new Interpretor());
            }catch(RuntimeException e){
                e.printStackTrace();
            }


        } else {
            System.err.println("COMMAND-LINE ERROR: too many arguments.");
            System.exit(1);
        }



    }
}
