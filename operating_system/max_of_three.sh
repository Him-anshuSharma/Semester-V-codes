echo "Enter a,b,c"
read a
read b
read c
if test $a -gt $b
then
if test $a -gt $c
then echo "$a is greatest"
else
echo "$c is greatest"
fi
elif test $b -gt $c
then echo "$b is greatest"
else
echo "$c is greatest"
fi
