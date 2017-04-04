package compiler488.codegen;

import javax.xml.stream.events.Characters;
import java.util.ArrayList;

/**
 * Created by isthisnagee on 3/30/17.
 * It would be better to rewrite this so that Instruction is an abstract (or parent) class,
 * and children inherit. So we can define a class for each instruction. This gives us an illusion of
 * types, where some take arguments as chars, ints, shorts, or no args.
 *
 * BUT, given the whole deadline fiasco...that won't be happening :)
 */
public class Instruction {
    private int startLine;
    private short code;
    private ArrayList<Short> args;
    private int lineNumber;

    public Instruction(short code, ArrayList<Short> args) {
        int startLine = 0;
        this.code = code;
        this.args = args;
    }

    public Instruction(short code, short arg1, short arg2) {
        int startLine = 0;
        this.code = code;
        this.args = new ArrayList<>();
        args.add(arg1);
        args.add(arg2);
    }

    public Instruction(short code, short arg) {
        int startLine = 0;
        this.code = code;
        this.args = new ArrayList<>();
        args.add(arg);
    }

    public Instruction(short code, char arg) {
        int startLine = 0;
        this.code = code;
        this.args = new ArrayList<>();
        args.add((short)arg);
    }

    public Instruction(short code) {
        int startLine = 0;
        this.code = code;
        this.args = new ArrayList<>();
    }

    public Instruction(short code, int arg) {
        this.code = code;
        this.args = new ArrayList<>();
        this.addNumberArg((short)arg);
    }

    public void addArg(short arg) {
        this.args.add(arg);
    }

    public ArrayList<Short> getArgs() {
        return args;
    }

    public short getCode() {
        return code;
    }

    public void addNumberArg(short num) {
        /* oh well, numbers will not be printed correctly */
        short notInstruction = num;
        this.args.add(notInstruction);
    }

    private String instructionString(short i) {
        switch (i) {
            case Short.MIN_VALUE: return "UNDEFINED";
            case  0: return "HALT"  ;
            case  1: return "ADDR"  ;
            case  2: return "LOAD"  ;
            case  3: return "STORE" ;
            case  4: return "PUSH"  ;
            case  5: return "PUSHMT";
            case  6: return "SETD"  ;
            case  7: return "POP"   ;
            case  8: return "POPN"  ;
            case  9: return "DUP"   ;
            case 10: return "DUPN"  ;
            case 11: return "BR"    ;
            case 12: return "BF"    ;
            case 13: return "NEG"   ;
            case 14: return "ADD"   ;
            case 15: return "SUB"   ;
            case 16: return "MUL"   ;
            case 17: return "DIV"   ;
            case 18: return "EQ"    ;
            case 19: return "LT"    ;
            case 20: return "OR"    ;
            case 21: return "SWAP"  ;
            case 22: return "READC" ;
            case 23: return "PRINTC";
            case 24: return "READI" ;
            case 25: return "PRINTI";
            case 26: return "TRON"  ;
            case 27: return "TROFF" ;
            case 28: return "ILIMIT";
            default: return new Short(i).toString();
        }
    }
    @Override
    public String toString() {
        String s = instructionString(code);
        for (short inst : args) {
            s += " " + instructionString(inst);
        }
        return s;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    public int getLineNumber() {
        return lineNumber;
    }
}
