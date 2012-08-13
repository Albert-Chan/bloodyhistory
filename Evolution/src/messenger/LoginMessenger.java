package messenger;

public class LoginMessenger extends AbstractMessenger {
	private static String Login;

	public boolean prepareParam(String... args) {
		assert (args.length == 4);
		Login.replace("@loginServer", args[0]);
		Login.replace("@gameServer", args[1]);
		Login.replace("@user", args[2]);
		Login.replace("@pass", args[3]);
		return true;
	}

	public int getResponse() {
		
	}
	
	/*<!-- The expected response of login: HTTP/1.1 302 Found Date: Thu, 10 Nov 
	2011 08:38:06 GMT Server: Apache Set-Cookie: session=6f6ff6fe1da6; path=/ 
	Expires: Thu, 19 Nov 1981 08:52:00 GMT Cache-Control: no-store, no-cache, 
	must-revalidate, post-check=0, pre-check=0 Pragma: no-cache Set-Cookie: prsess_134769=c7437244d22b392633effec4b8f1de2a; 
	expires=Fri, 11-Nov-2011 08:38:06 GMT; path=/ Set-Cookie: login_134769=U_tw13%3AAlbert%3Aacd393a47239facaadd31f7b0d9f3638; 
	expires=Fri, 11-Nov-2011 08:38:06 GMT; path=/ Location: http://uni13.ogame.tw/game/index.php?page=overview&session=6f6ff6fe1da6&kid= 
	Vary: Accept-Encoding Content-Length: 0 Connection: close Content-Type: text/html; 
	charset=utf-8 -->
	*/
	public String getCookie(){
//	Set-Cookie: session=6f6ff6fe1da6;
//	Set-Cookie: prsess_134769=c7437244d22b392633effec4b8f1de2a; 
//	Set-Cookie: login_134769=U_tw13%3AAlbert%3Aacd393a47239facaadd31f7b0d9f3638;
	}

}
