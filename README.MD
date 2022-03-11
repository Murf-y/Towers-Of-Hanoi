<div>
    <h1 style="text-align:center;font-size:50px;font-family:monospace;">🗼Towers Of Hanoi🗼</h1>
    <br>
    <h1>About the problem :</h1>
    <ul>
        <li style="font-size:20px;">
        There exists 3 Towers, numbered 1, 2 and 3 respectively.<br>
        First Tower contain <strong>N</strong> disks, where the disks are placed in decreasing order, starting from the bottom. For example: <br> 
            <pre>
    |           |           |
   *1*          |           |
  **2**         |           | 
---------   ---------   ---------</pre>
        </li>
        <li style="font-size:16px;">
        Your <l style="color:green;">goal</l> is to move all disks from the first tower (1) to the last tower (3), you can use the middle tower (2) as a transit tower, but you need to obey the <strong>Rules</strong>.
        </li>
        <li style="font-size:16px;">
        Rules:
            <ul>
                <li>Move only <l style="color:red">one</l> disk at a time.
                </li>
                <li>You cannot Place a disk with size <strong>n</strong>, on top of a disk with size <strong>m</strong>, where <l style="color:red">m > n</l>.
                <br>i.e You cannot place a big disk on a smaller one.
                </li>
                <li>There wont exists 2 disks <Strong>a</strong>,  <Strong>b</strong> where <br><l style="color:red">SizeOf(<Strong> a </strong>) </l>== <l style="color:red">SizeOf(<Strong> b </strong>) </l>.
                </li>
            </ul>
        </li>
        <li>
        Read More about the problem and details <a href="https://en.wikipedia.org/wiki/Tower_of_Hanoi">here</a>.
        </li>
    </ul>
    <h1>Solution</h1>
    <p style="font-size:16px;">This repository, contains the solution for Tower of Hanoi recursively and Itertively.</p>
    <p style="font-size:15px;">I will be explaining my attempt for the iterative solution.</p>
    <div>
    First, without lost of generality, lets assume that the number of disks is 3. And each disk is represented with a alphabetic character, such as the smallest disk start with <strong>A</strong><br>
    <pre>disk 1 => A
disk 2 => B
disk 3 => C</pre>
    We will initialize a variable to represent the number of moves done till now<pre>move_counter = 0;</pre> 
    Now we will convert this move_counter into <var>base-2</var>, and include 3 bits
    <pre>move_counter ==binary==> 000</pre>
    Here the magic begins,
    lets associate each bit to a disk, starting from the right most bit with the smallest disk.
    <pre>
    0      0       0
    |      |       |
    C      B       A</pre>
    </div>
    Now we start incrementing <var>move_counter</var> till we reach 2^n - 1, which is the Max number represented with 3 bits.
    <pre>move_counter += 1;
// In base 2 
    0       0       1
</pre>
    First rule, Each bit, that changes from 0 to 1, mean that we need to move the disk associated with that bit. To know the number of moves for disk A - and only for A - we investigate if the number of disks is even or not.<br>
    In this case, number of disks = 3 (odd), thus we move 2 steps to the right,
    if the number of disks was even then we only move 1 step to the right.<br>
    After we moved disk <strong>A</strong> to the right 2 steps, this is the state of the towers.
    <pre>
    |                   |                   |
  **2**                 |                   |
 ***3***                |                  *1*
_________           _________           _________
    </pre>
    Now we re increment <var>move_counter</var> by 1.
    <pre>move_counter += 1; // moves_counter = 2
// In base 2 
    0       1       0
</pre>
    Bit 1 changed from 1 to 0, we dont do anything about disk A, however, bit 2 changed from 0 to 1, thus we move disk <strong>B</strong>. Note that only disk A moves 2 (odd) steps or 1 (even) step, all other disk moves to the available tower respectively, thus we need to move disk  <strong>B</strong> to the first available tower, which is tower (2).
        <pre>
    |                   |                   |
    |                   |                   |
 ***3***              **2**                *1*
_________           _________           _________
    </pre>
    Now we re increment <var>move_counter</var> by 1.
    <pre>move_counter += 1; // moves_counter = 3
// In base 2 
    0       1       1
</pre>
Bit 1 changed from 0 to 1,thus we need to move disk  <strong>A</strong> two steps to the right, i.e to tower (2).
        <pre>
    |                   |                   |
    |                  *1*                  |
 ***3***              **2**                 |
_________           _________           _________
    </pre>
    This algorithm continues till move_counter reaches 2^n - 1.
    <br>
    I will put the moves without explaining.
        <pre>
// move_counter = 4           =>  1 0 0
    |                   |                   |
    |                  *1*                  |
    |                 **2**              ***3***
_________           _________           _________
    </pre>
        <pre>
// move_counter = 5           =>  1 0 1
    |                   |                   |
    |                   |                   |
   *1*                **2**              ***3***
_________           _________           _________
    </pre>
            <pre>
// move_counter = 6           =>  1 1 0
    |                   |                   |
    |                   |                 **2**
   *1*                  |                ***3***
_________           _________           _________
    </pre>
            <pre>
// move_counter = 7           =>  1 1 1
    |                   |                  *1*
    |                   |                 **2**
    |                   |                ***3***
_________           _________           _________
    </pre>
    Done.
</div>
<br>
<br>
<br>
<h1>
Code :
</h1>
Solution is implemented in java, you can download the source code and compile it. then run it to test the code.

<h1>
License:
</h1>
<a href="https://opensource.org/licenses/MIT" style="font-size:20px">MIT license</a><br>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.