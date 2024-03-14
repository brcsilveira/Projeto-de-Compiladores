import java.io.*;
import java.util.*;

import ast.*;

public class GeraCodigo {
    private static PrintWriter writer;

    public static void gerar(Prog prog) {
        try {
            writer = new PrintWriter("Saida.java", "UTF-8");

            for(Fun f : prog.fun){ 
                geraFun(f);
            }

            geraMain(prog.main);

            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Gera o código da classe Main
    public static void geraMain(Main main){
        writer.println("public static void main(String[] args) {");

        for(VarDecl vardecl: main.vars){
            geraVarDecl(vardecl);
        }

        geraComandos(main.coms);
        
        writer.println("}");
    }

    // Gera o código da classe Fun
    public static void geraFun(Fun fun){
        writer.println("public static " + fun.retorno + " " + fun.nome + "(");
        for(ParamFormalFun param: fun.params){
            writer.println(param.type + " " + param.var + ",");
        }
        writer.println(") {");

        for(VarDecl vardecl: fun.vars){
            geraVarDecl(vardecl);
        }

        geraComandos(fun.body);
        
        writer.println("}");
    }

    /**
     * public class Comando{}
     */
    public static void geraComandos(ArrayList<Comando> comandos){
        for(Comando comando: comandos){
            geraComando(comando);
        }
    }

    public static void geraComando(Comando comando){
        switch (comando.getClass().getSimpleName()) {
            case "CAtribuicao":
                geraAtribuicao((CAtribuicao)comando);
                break;
            case "CIf":
                geraIf((CIf)comando);
                break;
            case "CWhile":
                geraWhile((CWhile)comando);
                break;
            case "CPrint":
                geraPrint((CPrint)comando);
                break;
            case "CReturn":
                geraReturn((CReturn)comando);
                break;
            case "CReadInput":
                geraReadInput((CReadInput)comando);
                break;
            case "CChamadaFun":
                geraChamadaFun((CChamadaFun)comando);
                break;
            default:
                break;
        }
    }

    // Gera o código da classe CAtribuicao
    public static void geraAtribuicao(CAtribuicao atribuicao){
        writer.println(atribuicao.var + " = " + geraExp(atribuicao.exp) + ";");
    }

    // Gera o código da classe CIf
    public static void geraIf(CIf ifcmd){
        writer.println("if(" + geraExp(ifcmd.exp) + "){");
        geraComandos(ifcmd.bloco);
        writer.println("}");
    }

    // Gera o código da classe CWhile
    public static void geraWhile(CWhile whilecmd){
        writer.println("while(" + geraExp(whilecmd.exp) + "){");
        geraComandos(whilecmd.bloco);
        writer.println("}");
    }

    // Gera o código da classe CPrint
    public static void geraPrint(CPrint print){
        writer.println("System.out.println(" + geraExp(print.exp) + ");");
    }

    // Gera o código da classe CReturn
    public static void geraReturn(CReturn ret){
        writer.println("return " + geraExp(ret.exp) + ";");
    }

    // Gera o código da classe CReadInput
    public static void geraReadInput(CReadInput read){
        writer.println(read.var + " = " + "new Scanner(System.in).nextInt();");
    }

    // Gera o código da classe CChamadaFun
    public static void geraChamadaFun(CChamadaFun chamada){
        writer.println(chamada.fun + "(" + geraArgs(chamada.args) + ");");
    }

    public static String geraArgs(ArrayList<Exp> args){
        StringBuilder ret = new StringBuilder();
        for(Exp exp: args){
            ret.append(geraExp(exp)).append(",");
        }
        return ret.toString();
    }

    public static String geraExp(Exp exp){
        switch (exp.getClass().getSimpleName()) {
            case "EBool":
                return "boolean";
            case "ETrue":
                return "true";
            case "EFalse":
                return "false";
            case "EFloat":
                return String.valueOf(((EFloat)exp).value);
            case "EVar":
                return ((EVar)exp).var;
            case "EChamadaFun":
                return geraChamadaFun((EChamadaFun)exp);
            case "EOpExp":
                return geraOper((EOpExp)exp);
            default:
                return "";
        }
    }

    // Gera o código da classe EChamadaFun
    public static String geraChamadaFun(EChamadaFun chamada){
        StringBuilder ret = new StringBuilder(chamada.fun).append("(");
        for(Exp exp: chamada.args){
            ret.append(geraExp(exp)).append(",");
        }
        return ret + ")";
    }

    // Gera o código da classe EOpExp
    public static String geraOper(EOpExp op){
        return geraExp(op.arg1) + " " + op.op + " " + geraExp(op.arg2);
    }

    // Gera o código da classe VarDecl
    public static void geraVarDecl(VarDecl vardecl){
        writer.println(vardecl.type + " " + vardecl.var + ";");
    }
}