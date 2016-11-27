<?php
session_start();

		   $con=mysqli_connect("localhost","root","adivina123") or die ("sin conexion ");
		   mysqli_select_db($con,"calificaciones");
		    $usuario=$_GET["usuario"];
   		    $password=$_GET["password"];
   		    $ID_USUARIO=$_GET["ID_USUARIO"];
   		    $ID_CICLO=$_GET["ID_CICLO"];
   		    $ID_EMPRESA=$_GET["ID_EMPRESA"];
   		    $ID_FOLIO=$_GET["ID_FOLIO"];

   		  

if($_GET['opcion']==1){

  $sql="select usuario.ID_USUARIO ,USUARIO , aes_decrypt(PASSWORD,'masterkey')  as PASS from usuario	
                 where USUARIO= '".$usuario."' and PASSWORD = aes_encrypt('".base64_encode($password)."','masterkey')";

		   $rs=mysqli_query($con,$sql);


		   while($row=mysqli_fetch_array($rs))
			   $datos[]=$row;
 
		   print json_encode($datos);
		   
}

if ($_GET['opcion']==2){


$sql="select u.ID_USUARIO,a.ID_ALUMNOS, concat(p.NOMBRE,' ',p.PATERNO,' ',p.MATERNO) as NOMBRE, a.MATRICULA,gr.NOMBRE_GRADO as grado, g.NOMBRE_GRUPO as grupo,c.CICLO,nv.NOMBRE_NIVEL
	from usuario as u  
	inner join personas as p on u.ID_PERSONA=p.ID_PERSONA
	inner join alumnos as a on p.ID_PERSONA=a.ID_ALUMNOS
	inner join datos_alumnos as da on a.ID_ALUMNOS=da.ID_ALUMNOS 
	inner join datos_grupos as dg on da.ID_DATOGRUPO=dg.ID_DATOGRUPO
	inner join grupos as g on dg.ID_GRUPO=g.ID_GRUPO
	inner join grado as gr on dg.ID_GRADO=gr.ID_GRADO
	inner join ciclo_escolar as c on dg.ID_CICLO=c.ID_CICLO
	inner join nivel_educativo as nv on dg.ID_NIVEL=nv.ID_NIVEL
	where u.ID_USUARIO='".$ID_USUARIO."'";
	 $rs=mysqli_query($con,$sql);


		   while($row=mysqli_fetch_array($rs))
			   $datos[]=$row;
		
 
		   print json_encode($datos);
 
}

if ($_GET['opcion']==3){
$_SESSION = array();
$sql="	select  asig.ID_ASIGNATURA as 'CLAVE_ASIGNATURA', 
				    asig.NOMBRE_ASIGNATURA,
				    concat(p.NOMBRE,' ',p.PATERNO,' ',p.MATERNO) as NOMBRE,
				    a.MATRICULA, 
				    gr.NOMBRE_GRADO as grado, 
				    g.NOMBRE_GRUPO as grupo,
				    c1.CICLO,
				    nv.NOMBRE_NIVEL,
				    u.USUARIO, 
				    P1, P2, P3,P4,P5,
				    a.ID_ALUMNOS,
				    c1.ID_CICLO,
				    e.ID_EMPRESA

from usuario as u  inner join personas as p on u.ID_PERSONA=p.ID_PERSONA
inner join alumnos as a on p.ID_PERSONA=a.ID_PERSONA
inner join calificaciones as  c on c.ID_ALUMNOS=a.ID_ALUMNOS
inner join asignatura as asig on asig.ID_ASIGNATURA=c.ID_ASIGNATURA
inner join detalle_asignatura  as dasig on dasig.ID_ASIGNATURA=asig.ID_ASIGNATURA
inner join datos_grupos as dg on dg.ID_DATOGRUPO=dasig.ID_DATOGRUPO
inner join grupos as g on dg.ID_GRUPO=g.ID_GRUPO
inner join grado as gr on dg.ID_GRADO=gr.ID_GRADO
inner join nivel_educativo as nv on dg.ID_NIVEL=nv.ID_NIVEL
inner join ciclo_escolar as c1 on dg.ID_CICLO=c1.ID_CICLO
inner join empresa as e on e.ID_EMPRESA=asig.ID_EMPRESA
where u.ID_USUARIO='".$ID_USUARIO."'";
	$rs=mysqli_query($con,$sql);
	   $i=0;
	while ($row=mysqli_fetch_array($rs)) {	
		//$datos[$i]=array("ID_ASIGNATURA"=>$row[0],"NOMBRE_ASIGNATURA"o=>$rw[1]);
		$datos[$i]=array("ID_ASIGNATURA"=>$row[0],"NOMBRE_ASIGNATURA"=>$row[1], "NOMBRE_USUARIO" => $row[2], "MATRICULA" => $row[3], "GRADO"=>$row[4], "NOMBRE_GRUPO"=> $row[5], "CICLO" => $row[6], "NOMBRE_NIVEL" => $row[7], "CORREO" => $row[8], "PARCIAL1" => $row[9], "PARCIAL2" => $row[10], "PARCIAL3" => $row[11], "PARCIAL4" => $row[12], "PARCIAL5" => $row[13],"ID_ALUMNOS"=>$row[14],"ID_CICLO"=>$row[15],"ID_EMPRESA"=>$row[16]);
			//$_SESSION["id_a"]=$row[14];
			//$_SESSION["id_c"]=$row[15];
			//$_SESSION["id_e"]=$row[16];
			//$GLOBALS['ID_ALUMNOS'] = $row[14];
   			//$GLOBALS['ID_CICLO'] = $row[15];
   			//$GLOBALS['ID_EMPRESA'] = $row[16];

		//$datos[$i]=array($row);	
		$i=$i+1; 
	}
	print json_encode($datos);
	//echo '<script>window.open("login.php?opcion=4","_blank")</script>';
}
if ($_GET['opcion']==4){

	$sql="select f.id_folio,concat('pago de colegiatura del mes:',m.nombre) as historial,f.fecha,f.total,cn.precio,f.subtotal,f.descuento,
f.recargo,'1' as cantidad, e.LOGO,e.COLEGIO,e.COLEGIO_DIRECCION,e.CORREO,e.COLEGIO_CONTACTO,concat(per.NOMBRE,' ',per.PATERNO,' ',per.MATERNO) as NOMBRE,
gr.NOMBRE_GRADO as grado, g.NOMBRE_GRUPO as grupo,a.MATRICULA,n.NOMBRE_NIVEL as escolaridad
from folio as f 
inner join colegiatura as c on f.id_folio=c.id_folio
inner join meses m on c.id_mes=m.id_mes
inner join costo_nivel as cn on c.id_costo_nivel=cn.id_costo_nivel 
inner join empresa as e on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where u.ID_USUARIO=$ID_USUARIO and f.id_empresa=$ID_EMPRESA and f.id_ciclo=$ID_CICLO and f.id_folio=$ID_FOLIO
union 
select f.id_folio, df.descripcion,f.fecha,f.total,df.precio,f.subtotal,f.descuento,f.recargo,df.cantidad,e.LOGO,
e.COLEGIO,e.COLEGIO_DIRECCION,e.CORREO,e.COLEGIO_CONTACTO,concat(per.NOMBRE,' ',per.PATERNO,' ',per.MATERNO) as NOMBRE,
gr.NOMBRE_GRADO as grado, g.NOMBRE_GRUPO as grupo,a.MATRICULA,n.NOMBRE_NIVEL as escolaridad
from  folio as f
inner join detalle_folio as df  on f.id_folio=df.id_folio
inner join productos as p on p.id_producto=df.id_producto
inner join empresa as e  on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where u.ID_USUARIO=$ID_USUARIO and f.id_empresa=$ID_EMPRESA and f.id_ciclo=$ID_CICLO and f.id_folio=$ID_FOLIO";

	$rs=mysqli_query($con,$sql);
	   $i=0;
	   while ($row=mysqli_fetch_array($rs)) {	

	   	$datos[$i]=array("id_folio"=>$row[0],"historial"=>$row[1],"fecha"=>$row[2],"total"=>$row[3],"precio"=>$row[4],
	   		"subtotal"=>$row[5],"descuento"=>$row[6],"recargo"=>$row[7],"cantidad"=>$row[8],"LOGO"=>$row[9],"COLEGIO"=>$row[10],
	   		"COLEGIO_DIRECCION"=>$row[11],"CORREO"=>$row[12],"COLEGIO_CONTACTO"=>$row[13],"NOMBRE"=>$row[14],"grado"=>$row[15],
	   		"grupo"=>$row[16],"MATRICULA"=>$row[17],"escolaridad"=>$row[18]);
	   		$i=$i+1; 
	   	}
	   		$rs = mysqli_query($con,$sql) or die (mysql_error());
if (mysqli_num_rows($rs)>0)
{
	print json_encode($datos);
}else {
	print ('No se encontraron datos');
}
}

if ($_GET['opcion']==5){
$sql="select f.id_folio,f.fecha,f.total
from folio as f 
inner join colegiatura as c on f.id_folio=c.id_folio
inner join meses m on c.id_mes=m.id_mes
inner join costo_nivel as cn on c.id_costo_nivel=cn.id_costo_nivel 
inner join empresa as e on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where u.ID_USUARIO=$ID_USUARIO 
union 
select f.id_folio,f.fecha,f.total
from  folio as f
inner join detalle_folio as df  on f.id_folio=df.id_folio
inner join productos as p on p.id_producto=df.id_producto
inner join empresa as e  on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where u.ID_USUARIO=$ID_USUARIO ";

$rs=mysqli_query($con,$sql);
	   $i=0;
	   while ($row=mysqli_fetch_array($rs)) {	

	   	$datos[$i]=array("id_folio"=>$row[0],"fecha"=>$row[1],"total"=>$row[2]);
	   		$i=$i+1; 
	   	}
	   		$rs = mysqli_query($con,$sql) or die (mysql_error());
if (mysqli_num_rows($rs)>0)
{
	print json_encode($datos);
}else {
	print ('No se encontraron datos');
}
}

if ($_GET['opcion']==6) {
	$sql="select '1' as cantidad, concat('pago de colegiatura del mes:',m.nombre) as descripcion,cn.precio
from folio as f
inner join colegiatura as c on f.id_folio=c.id_folio
inner join meses m on c.id_mes=m.id_mes
inner join costo_nivel as cn on c.id_costo_nivel=cn.id_costo_nivel 
inner join empresa as e on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where  f.id_folio=$ID_FOLIO
union 
select df.cantidad as cantidad,df.descripcion,df.precio
from  folio as f
inner join detalle_folio as df  on f.id_folio=df.id_folio
inner join productos as p on p.id_producto=df.id_producto
inner join empresa as e  on f.id_empresa=e.ID_EMPRESA
inner join alumnos as a on a.ID_ALUMNOS=f.id_alumnos
inner join personas as per on per.ID_PERSONA=a.ID_PERSONA
inner join usuario as u on u.ID_PERSONA=per.ID_PERSONA
inner join datos_alumnos as da on da.ID_ALUMNOS=a.ID_ALUMNOS
inner join datos_grupos as dg on dg.ID_DATOGRUPO=da.ID_DATOGRUPO
inner join grupos as g on g.ID_GRUPO=dg.ID_GRUPO
inner join grado as gr on gr.ID_GRADO=dg.ID_GRADO
inner join nivel_educativo as n on n.ID_NIVEL=dg.ID_NIVEL
inner join ciclo_escolar as ci on ci.ID_CICLO=dg.ID_CICLO
where f.id_folio=$ID_FOLIO";
$rs=mysqli_query($con,$sql);
	   $i=0;
	   while ($row=mysqli_fetch_array($rs)) {	

	   	$datos[$i]=array("cantidad"=>$row[0],"descripcion"=>$row[1],"precio"=>$row[2]);
	   		$i=$i+1; 
	   	}
	   		$rs = mysqli_query($con,$sql) or die (mysql_error());
if (mysqli_num_rows($rs)>0)
{
	print json_encode($datos);
}else {
	print ('No se encontraron datos');
}

}


/*

if ($_GET['opcion']==4){

	$sql= "select id_folio,id_usuario,group_concat(' ',des) as historial,fecha,total,subtotal,descuento,recargo,
case when estatus=0 then 'Sin pagar' when estatus=1 then 'Pagado' End as status 
from
(
(select f.id_folio as id_folio,pf.id_usuario as id_usuario,
concat('pago de mes:',m.nombre) as des,date(f.fecha) as fecha,f.total as total,
f.subtotal as subtotal,f.descuento as descuento,f.recargo as recargo,f.pagado as estatus 
from 
folio as f  
inner join colegiatura as c  on f.id_folio=c.id_folio
inner join meses as m on c.id_mes=m.id_mes
inner join pago_folio as pf on pf.id_folio=f.id_folio
where pf.id_usuario=$ID_USUARIO and f.id_ciclo= $ID_CICLO 	
)union 
(select f.id_folio as id_folio,pf.id_usuario as id_usuario,pro.descripcion as des, date(f.fecha) as fecha,f.total as total,
f.subtotal as subtotal,f.descuento as descuento,f.recargo as recargo,f.pagado as estatus
from folio as  f 
inner join detalle_folio as df on f.id_folio=df.id_folio
inner join productos as pro on  pro.id_producto=df.id_producto
inner join pago_folio as pf on pf.id_folio=f.id_folio
where pf.id_usuario=$ID_USUARIO and f.id_ciclo=$ID_CICLO 
)
) as tablauni
group by id_folio ";
	$rs=mysqli_query($con,$sql);
	   $i=0;
	   while ($row=mysqli_fetch_array($rs)) {	
		
		//$datos[$i]=array("ID_ASIGNATURA"=>$row[0],"NOMBRE_ASIGNATURA"o=>$rw[1]);
		

		$datos[$i]=array("id_folio"=>$row[0],"id_usuario"=>$row[1],"historial"=>$row[2],"fecha"=>$row[3],"total"=>$row[4],"subtotal"=>$row[5],"descuento"=>$row[6],"recargo"=>$row[7],"status"=>$row[8]);
			$i=$i+1; 
	}

$rs = mysqli_query($con,$sql) or die (mysql_error());
if (mysqli_num_rows($rs)>0)
{
	print json_encode($datos);
}else {
	print ('No se encontraron datos');
}
	
}
if ($_GET['opcion']==5){

$sql="select f.id_folio,concat('pago de colegiatura del mes:',m.nombre),f.fecha,f.total,cn.precio,f.subtotal,f.descuento,
f.recargo,'1' as cantidad, e.LOGO,e.COLEGIO,e.COLEGIO_DIRECCION,e.CORREO,e.COLEGIO_CONTACTO 
from folio as f 
inner join colegiatura as c on f.id_folio=c.id_folio
inner join meses m on c.id_mes=m.id_mes
inner join costo_nivel as cn on c.id_costo_nivel=cn.id_costo_nivel 
inner join empresa as e on f.id_empresa=e.ID_EMPRESA	
where f.id_empresa=$ID_EMPRESA and f.id_ciclo=$ID_CICLO and f.id_folio=$ID_FOLIO
union 
select f.id_folio, df.descripcion,f.fecha,f.total,df.precio,f.subtotal,f.descuento,f.recargo,df.cantidad,e.LOGO,
e.COLEGIO,e.COLEGIO_DIRECCION,e.CORREO,e.COLEGIO_CONTACTO
from  folio as f
inner join detalle_folio as df  on f.id_folio=df.id_folio
inner join productos as p on p.id_producto=df.id_producto
inner join empresa as e  on f.id_empresa=e.ID_EMPRESA
where f.id_empresa=$ID_EMPRESA and f.id_ciclo=$ID_CICLO and f.id_folio=$ID_FOLIO";

	$rs=mysqli_query($con,$sql);
	   $i=0;
	   while ($row=mysqli_fetch_array($rs)) {	

	   	$datos[$i]=array("id_folio"=>$row[0],"historial"=>$row[1],"fecha"=>$row[2],"total"=>$row[3],"precio"=>$row[4],
	   		"subtotal"=>$row[5],"descuento"=>$row[6],"recargo"=>$row[7],"cantidad"=>$row[8],"LOGO"=>$row[9],"COLEGIO"=>$row[10],
	   		"COLEGIO_DIRECCION"=>$row[11],"CORREO"=>$row[12],"COLEGIO_CONTACTO"=>$row[13]);
	   		$i=$i+1; 
	   	}
	   		$rs = mysqli_query($con,$sql) or die (mysql_error());
if (mysqli_num_rows($rs)>0)
{
	print json_encode($datos);
}else {
	print ('No se encontraron datos');
}
}*/


?>
