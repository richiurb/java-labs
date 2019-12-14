Run tests:
    javac base\*.java expression\*.java
    java -ea expression.TripleExpressionTest hard





String stra = 
a.getPriority() < getPriority() - 1 
|| (a.getPriority() - 1 <= getPriority() - 2 && (a.getPriority() == 1 || a.getPriority() == 3)) 
? String.format("(%s)", a.toMiniString()) 
: a.toMiniString();

String strb = 
b.getPriority() < getPriority()
|| (b.getPriority() - 1 <= getPriority() && (b.getPriority() == 1 || b.getPriority() == 3)) 
? String.format("(%s)", b.toMiniString()) 
: b.toMiniString();