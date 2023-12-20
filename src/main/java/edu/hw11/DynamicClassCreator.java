package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;

public final class DynamicClassCreator {
    private DynamicClassCreator() {
    }

    public static Class<?> create() {
        return new ByteBuddy()
            .subclass(Object.class)
            .name("FibonacciCounter")
            .defineMethod("fib", long.class, Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new FibonacciCounter()))
            .make()
            .load(ClassLoader.getSystemClassLoader())
            .getLoaded();
    }

    private static class FibonacciCounter implements ByteCodeAppender {
        @Override
        @SuppressWarnings("MagicNumber")
        public @NotNull Size apply(
            @NotNull MethodVisitor methodVisitor,
            Implementation.@NotNull Context context,
            @NotNull MethodDescription methodDescription
        ) {
            Label l0 = new Label();
            Label l1 = new Label();
            Label l2 = new Label();
            methodVisitor.visitCode();

            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IFNE, l0);
            methodVisitor.visitInsn(Opcodes.LCONST_0);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitFrame(
                Opcodes.F_FULL,
                1, new Object[] {Opcodes.INTEGER},
                0, new Object[] {}
            );
            methodVisitor.visitLabel(l0);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);
            methodVisitor.visitInsn(Opcodes.LCONST_1);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitVarInsn(Opcodes.ISTORE, 5);

            methodVisitor.visitFrame(
                Opcodes.F_FULL,
                4, new Object[] {Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
                0, new Object[] {}
            );
            methodVisitor.visitLabel(l1);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 5);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 0);
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGE, l2);

            methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 6);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 1);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
            methodVisitor.visitInsn(Opcodes.LADD);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 3);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 6);
            methodVisitor.visitVarInsn(Opcodes.LSTORE, 1);

            methodVisitor.visitIincInsn(5, 1);
            methodVisitor.visitJumpInsn(Opcodes.GOTO, l1);

            methodVisitor.visitFrame(
                Opcodes.F_FULL,
                4, new Object[] {Opcodes.INTEGER, Opcodes.LONG, Opcodes.LONG, Opcodes.INTEGER},
                0, new Object[] {}
            );
            methodVisitor.visitLabel(l2);
            methodVisitor.visitVarInsn(Opcodes.LLOAD, 3);
            methodVisitor.visitInsn(Opcodes.LRETURN);

            methodVisitor.visitEnd();

            return new Size(4, 8);
        }
    }
}
