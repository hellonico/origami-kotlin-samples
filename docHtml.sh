#!/bin/bash

# bash splits on space and new lines by default
IFS=$'\n'       # make newlines the only separator
# set -f          # disable globbing

for i in `find . -name "*.kt"` ; do
  # title
  echo -n "## "
  group=`echo $i | rev | cut -d'/' -f 2 | rev`
  title=`echo $i | rev | cut -d'/' -f 1 | rev`
  echo "$group > $title"

  echo "<a href=\"$i\">$title</a>"

  # code
  echo '```java'
  cat $i | grep -v import | grep -v package | grep -v "//" | grep -v -e '^$' | head -n2
  echo '```'

  # pictures in
  INS=`cat $i | grep -v import | grep imread`
  for out in $INS ; do
    img=`echo $out| head -n1 | cut -d"\"" -f2`
    imgt=`echo $img | cut -d"/" -f2`
    echo "**< $imgt**"
    echo ""
    echo "<img src=\"$img\" height=25% width=25%/>"
    echo ""
  done

  # pictures out
  OUTS=`cat $i | grep -v import | grep imwrite`
  for out in $OUTS ; do
    img=`echo $out| head -n1 | cut -d"\"" -f2`
    imgt=`echo $img | cut -d"/" -f2`
    echo "**> $imgt**"
    echo ""
    echo "<img src=\"$img\" height=25% width=25%/>"
    echo ""
  done

done