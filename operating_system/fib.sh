a=0
b=1
echo 'Enter N'
read N
for((i=0;i<N;i++))
do 
echo "$a"
f2=$((a+b))
a=$b
b=$f2
done
