/**
 * Created by iya on 10/7/16.
 */

var fs = require('fs');

var data = [];
var inv = 0;

function doAsynchIO(callback){
    fs.readFile(process.argv[2], function (err, fBuffer) {
        if (err) return console.error(err);
        data = fBuffer.toString().split("\n");
        data.length = data.length - 1;

        for(var i = 0; i < data.length; i++){
            data[i] = Number(data[i]); // bring to number
        }
        console.log(data);

        callback(data);

        console.log("data length: " + data.length);
        console.log("Inversions: " + inv);

    });
}

function logResult(){
    console.log(data.length);
    //console.log(data[99999]);

}

function mergeSort(d){
    //check for base case
    if (d.length < 2)
        return d;

    // split the array
    var cut = Math.floor(d.length / 2);
    console.log("cut: " + cut);
    var d_left = d.slice(0, cut);
    var d_right = d.slice(cut, d.length);

    return merge(mergeSort(d_left), mergeSort(d_right));
}

function merge(a, b){
    console.log("Merging:");
    console.log(a);
    console.log(b);
    var c = [];
    var i = 0;
    var j = 0;
    while (i < a.length && j < b.length){
        if (a[i] < b[j]){
            c.push(a[i]);
            i++;
        } else {
            c.push(b[j]);
            j++;
            inv += a.length - i;
        }
    }
    if (i < a.length){
        c = c.concat( a.slice(i, a.length) );
    }
    if (j < b.length){
        c = c.concat( b.slice(j, b.length) );
    }

    console.log("Merged: ");
    console.log(c);

    return c;
}

//doAsynchIO(logResult);

//doAsynchIO(countInversions);

doAsynchIO(mergeSort);