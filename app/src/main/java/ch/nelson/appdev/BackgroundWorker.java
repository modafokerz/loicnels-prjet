package ch.nelson.appdev;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Cette classe a pour but de permettre la connection a la db avec le login et le pwd
 */
public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    AlertDialog alertD;
    String email="" ;
    String password="" ;

    BackgroundWorker (Context ctx)
    {
        context=ctx;
    }

    /**
     * On envoie l'email et le pwd et cela nous retourne si la connection est ok
     * @param params
     * @return resultat
     */
    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://y54uotiox.preview.infomaniak.website/connection_db.php";
        if (type.equals("login"))
        {
            try {
                email = params[1];
                password = params[2];

                URL url = new URL(login_url);
                HttpURLConnection httpconn = (HttpURLConnection)url.openConnection();
                httpconn.setRequestMethod("POST");
                httpconn.setDoOutput(true);
                httpconn.setDoInput(true);
                OutputStream opStream = httpconn.getOutputStream();
                BufferedWriter bfWriter = new BufferedWriter(new OutputStreamWriter(opStream,"UTF-8"));

                String post_data = URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");

                bfWriter.write(post_data);
                bfWriter.flush();
                bfWriter.close();
                opStream.close();

                InputStream inStream = httpconn.getInputStream();
                BufferedReader bfReader = new BufferedReader(new InputStreamReader(inStream,"iso-8859-1"));
                String resultat="";
                String line="";

                while ((line = bfReader.readLine())!=null)
                {
                    resultat += line;
                }
                bfReader.close();
                inStream.close();
                httpconn.disconnect();
                return resultat;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
       alertD = new AlertDialog.Builder(context).create();
       alertD.setTitle("Login Status");

    }

    /**
     * Affiche le resultat dans un alertDialog
     * @param result
     */
    @Override
    protected void onPostExecute(String result) {
        Session userSession = new Session(context);
       // alertD.setMessage(result);
        if (result.equals("ConnectionOk"))
        {
            userSession.setEmail(email);
            userSession.setPassword(password);
            userSession.setIsConnected(1);

        }
        else
        {
            userSession.setEmail(null);
            userSession.setPassword(null);
            userSession.setIsConnected(0);
            alertD.setMessage("Erreur dans les identifiants");
            alertD.show();
        }


    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
