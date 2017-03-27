import memlang.syntax.Interpretor;
import memlang.syntax.lexer.Lexer;
import memlang.syntax.lexer.LexerException;
import memlang.syntax.node.Node;
import memlang.syntax.parser.Parser;
import memlang.syntax.parser.ParserException;

import java.io.*;

/**
 * Created by pdesl on 2017-03-25.
 */
public class MemLangInterpretor {

    public static void main(String[] args){
        PushbackReader in = null;

        if (args.length == 0) {
            Reader input = new InputStreamReader(System.in);
            in = new PushbackReader(input, 1024);
        } else if (args.length == 1) {

            try {
                in = new PushbackReader(new FileReader(args[0]), 1024);
            }
            catch (FileNotFoundException e) {
                System.err.println(
                        "INPUT ERROR: file not found '" + args[0] + "'.");
                System.exit(1);
            }
        } else {
            System.err.println("COMMAND-LINE ERROR: too many arguments.");
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

    }
}
