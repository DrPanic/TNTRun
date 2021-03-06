package tntrun;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.bukkit.Bukkit;

public class VersionChecker {

	private static VersionChecker instance;

	public VersionChecker() {
		instance = this;
	}

	public static VersionChecker get() {
		return instance;
	}

	public String getVersion() {
		try {
			HttpURLConnection con = (HttpURLConnection) new URL("https://www.spigotmc.org/api/general.php").openConnection();
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			con.getOutputStream().write(("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=53359").getBytes("UTF-8"));
			String version = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
			if (version.length() <= 7) {
				return version;
			}
		} catch (Exception ex) {
			Bukkit.getLogger().info("[TNTRun_reloaded] Failed to check for update on Spigot");
		}
		return "error";
	}
}
