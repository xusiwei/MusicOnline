<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
		<link rel="stylesheet" type="text/css" href="styles.css">
		<link rel="stylesheet" type="text/css" href="css/reset.css">
		<link rel="stylesheet" type="text/css" href="css/button.css">
		-->
		<title>AHPU听吧</title>
        <script>
        	
			<s:if test="playIndex==null">
				var curPlay = null;
			</s:if>
			<s:else>
				var curPlay = <s:property value="playIndex"/>;
			</s:else>
        	// var curPlay = 0;
        	// Song.toString() 是JSON格式，方便客户端取数据
        	var audioSrcList = ${CURRENT_SONG_LIST}; 
        	var cycleAll = true;
        	
        	// 将歌曲信息显示到 label上
        	function showInfo(sidx) {
 				var objLblName = document.getElementById('lblName');
                var objLblDesc = document.getElementById('lblDesc');
                
                objLblName.innerHTML = audioSrcList[sidx].name;
                objLblDesc.innerHTML = audioSrcList[sidx].description;    
                objLblName.innerText = audioSrcList[sidx].name;
                objLblDesc.innerText = audioSrcList[sidx].description;
        	}
        	
        	// 用 audio 播放歌曲
            function playSong(sidx) {            	
            	var objAudio = document.getElementById('player');
                
                objAudio.src = audioSrcList[sidx].url;
                objAudio.play();
            }
            
            // 将第一首歌曲的信息显示出来
            function preLoad() { 
            	if(curPlay == null) curPlay = 0;
            	showInfo(curPlay);
            	<s:if test="audioSrc==null">
            	playSong(curPlay);
            	</s:if>
            }
            
            function playNext() {
            	++curPlay;
            	if(curPlay == audioSrcList.length) {
            		curPlay = 0;
            		if(!cycleAll) {
            			return;
            		}
                    var replay = confirm('已经是最后一首歌了，是否重头播放？');
                    // if( replay == true ) curPlay = 0;
                }
            	showInfo(curPlay);
                playSong(curPlay);
            }
            
            function singleLoop() {
                var objChk = document.getElementById('chkSingle');
                var objAudio = document.getElementById('player');
                if(objChk.checked) {
                    alert('你选择了单曲循环！');
                    objAudio.loop = "loop";
                   	cycleAll = false; 
                }
                else {
                    alert('你取消了单曲循环');
                    objAudio.loop = null;
                    cycleAll = true;
                }
            }
            
            var basePath = '<%=basePath%>';
            function goGood() {
				self.location.href = basePath + 'song_goodSong?playIndex=' + curPlay;
            }
            
            function goBad() {
                self.location.href = basePath + 'song_badSong?playIndex=' + curPlay;
            }
            
        </script>
	</head>

	<body>
		<s:include value="title.jsp"/>
		<div align="center">
			<h1>播放音乐</h1>
			
			<!-- 这里显示当前歌曲信息 -->
			正在播放：<label id="lblName"></label> <br/>
			歌曲简介：<label id="lblDesc"></label> <br/>

			<!-- 这里是播放器 -->
			<div>
                <audio id="player" src="<s:property value="audioSrc"/>" controls autoplay
                       onended="playNext();" >
                <script type="text/javascript">preLoad();</script>
                    <h1 style="color:red">你的浏览器不支持audio标签!<br/>
                   	 赶快<a href="http://www.google.com/intl/zh-CN/chrome/">下载最新新版的Chrome</a>吧！</h1>
                </audio> <br/>
                <!--缺少的控件-->
                <input id="chkSingle" type="checkbox" onclick="singleLoop();">单曲循环</input>
                <input id="btnNext" type="button" onclick="playNext();" value="下一首"/> <br/>
                
                <input id="btnGood" type="button" value="顶一下" onclick="goGood();">
                <input id="btnBad"  type="button" value="踩一下" onclick="goBad();">

<!--                
                <div align="center">
                    <input id="brnCycleMode"  type="button" value="单曲循环"
                           onclick="if(this.value=='单曲循环') this.value='全部循环'; else this.value='单曲循环';" /> <br/>
                    
                    <s:radio name="cycleMode" list="{'单曲循环','全部循环'}"></s:radio>
                    <s:radio name="playMode" list="{'顺序播放','随机播放'}"></s:radio>
                </div>
-->
			</div>
			
			<br/>
			<a href="go_songs">返回歌曲列表</a>
			
			<div align="center" style="width=60%">

			<!-- 歌曲列表开始 -->
			<s:if test="#session.CURRENT_SONG_LIST!=null">  <!-- 如果歌曲不止一首，显示播放列表 -->
				<s:if test="#session.CURRENT_SONG_LIST.size()>0"> 
				<div align="left" style="margin-left: 25%;">当前播放：</div>
				<table id="tblSong"  border="0" width="50%">
					<tr bgcolor="#7f7fff">
						<td>
							序号
						</td>
						<td>
							歌名
						</td>
						<td>
							歌手
						</td>
						<td>
							风格
						</td>
						<td>
							操作
						</td>
					</tr>

					<s:iterator value="#session.CURRENT_SONG_LIST" status="st">
						<s:if test="#st.odd">
							<s:set var="color" value="'#cccccc'" />
						</s:if>
						<s:else>
							<s:set var="color" value="'#eeeeee'" />
						</s:else>

						<tr bgcolor="<s:property value="color"/>">
							<td>	
								<s:property value="#st.count" />											
								<!-- 
								<s:checkbox name="selectList" value="#st.count"></s:checkbox>
								<s:property value="#st.count" />
								-->								
							</td>
							<td>
								<a href="playSong?audioSrc=<s:property value="savepath"/>">
									<s:property value="name" />
								</a>
							</td>
							<td>
								<s:property value="singer" />
							</td>
							<td>
								<s:property value="style" />
							</td>
							<td>
								<a href="song_removeItem?index=<s:property value="#st.count"/>">
									删除
								</a>	
							</td>
						</tr>
					</s:iterator>
				</table>
				</s:if>
			</s:if>
			<!-- 歌曲列表结束 -->
			</div>
		</div>
	</body>
</html>
