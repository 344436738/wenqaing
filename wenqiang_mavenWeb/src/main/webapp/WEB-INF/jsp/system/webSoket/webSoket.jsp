<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"  
               "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">  
<html xmlns="http://www.w3.org/1999/xhtml">  
<head>  
    <title>Mapbar 地图 JavaScript API 示例: 简单的地图</title>  
    <script type="text/javascript" src="http://union.mapbar.com/apis/maps/free?f=mapi&v=31.3&k=aCW9cItqL7sqT7AxaB0zdHZoZSWmbBsuT7JhMHTsMeD6ZIl9NzFsZHT=@JBL979@Iu7lJJZWWq0IDu9xZMzMxq7I9AhH7LAAA6hqzZHZZLTbZZauxlDz7C7DD9ZCFGT="></script>  
    <script type="text/javascript">  
        var maplet = null;  
        maplet = new Maplet("mapbar");  
        maplet.centerAndZoom(new MPoint(116.38672,39.90805), 8); 
       
          
           
            maplet.addControl(new MStandardControl());  
            var obj = maplet.getViewBound();  
            s = "西北角：" + obj.LeftUp + "\r\n" + "东北角：" + obj.RightUp + "\r\n" +  
            "东南角：" + obj.RightDown + "\r\n" + "西南角：" + obj.LeftDown;  
            alert(s); 
        		maplet.resize(document.documentElement.clientWidth,document.documentElement.clientHeight);
            
        
        function initMap(){  
     
        
  
            window.setTimeout(function(){maplet.panTo(200,0)},2000);  
            var marker = new MMarker(  
                    new MPoint(116.38672,39.90805),  
                    new MIcon("/wenqiang_mavenWeb/uploadFiles/uploadImgs/watermark.png",32,32)  
                );  
      
                maplet.addOverlay(marker);  
        }  
        
        
        
    </script>  
</head>  
  
<body onload="initMap()">  
<div id="mapbar" ></div>  
</body>  
</html>  