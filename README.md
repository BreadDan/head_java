UNIXコマンドheadをJavaで再現

head_java (-n [lines]) filename1 filename2 ...

"-n [lines]"或いは"--lines [lines]"で行数が指定できます、デフォルト行数は10
複数のファイル名で順番表示することができます
 
コンパイル：
javac head_java.java

コマンドライン実行
java head_java (-n [lines]) filename1 (filename2 ...)
