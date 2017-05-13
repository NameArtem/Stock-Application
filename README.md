# Stock-Application

This repository is a reference to create a simple stock application with java and mysql as database. The application simulates how to store and retrieve stock data to and from database.

<h3>Requirements:</h3>
<p> 1. <a href="http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html" target="_blank">Java SE Development Kit 8 (JDK).</a> </p> 
<p> 2. <a href="https://netbeans.org/downloads/" target="_blank">Netbeans IDE (optional)</a>, you could also swicth to your favorite IDE</p> 
<p> 2. <a href="http://www.java2s.com/Code/Jar/m/Downloadmysqlconnectorjava5123binjar.htm" target="_blank"> mysql-connector-java </a> is used in order to connect java to mysql </p>
<p> 3. <a href="http://www.jtattoo.net/Download.html" target="_blank">JTattoo Library (optional)</a> to change the app GUI's look and feel  </p>
<p> 4. <a href="https://poi.apache.org/" target="_blank">Apache POI</a> to export the output into excel file </p>


<h3> Database Setting:</h3>

<p>This application used database and table called stock and stock data, respectively.
You could also modify the database and table name and make a little bit adjustment in database and stock classes, just if you want to.
Don't edit the table structure without making some adjustments, otherwise the program will not work. 
</p>

<strong> Table structure: </strong>

<table>
  
  <thead>
  <tr>
  <th>Field</th>
  <th>Type</th>
  <th>Null</th>
  <th>Key</th>
  <th>Default</th>
  </tr>
  </thead>
  
  <tbody>
  <tr>
  <td>product_code</td>
  <td>VARCHAR(3)</td>
  <td>NO</td>
  <td>Primary Key</td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>product_name</td>
  <td>VARCHAR(90)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>uom</td>
  <td>VARCHAR(15)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>cogs</td>
  <td>BIGINT(20)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>selling_price</td>
  <td>BIGINT(20)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  <tr>
  <td>stock</td>
  <td>INT(11)</td>
  <td>NO</td>
  <td></td>
  <td>NULL</td>
  </tr>
  
  </tbody>
  
  </table>
  
