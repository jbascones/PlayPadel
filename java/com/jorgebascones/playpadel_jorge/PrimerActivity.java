package com.jorgebascones.playpadel_jorge;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class PrimerActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{


    String LayoutActual = "";

    //  ATRIBUTOS MIS PARTIDOS

    ListView listView ;
    String [] datos;
    ArrayList<Partido> misPartidos = new ArrayList<>();
    //Simulador simulador = new Simulador();
    ArrayList<String> misPartidosRutasList = new ArrayList<>();

    //  ATRIBUTOS CREAR PARTIDO

    private DatePicker dpResult;
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    int numeroTodosPartidos;

    //private int baseline;

    //private TimePicker tmResult;

    // ATRIBUTOS MI PERFIL
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();




    String miNombre;

    Usuario yo;

    // ATRIBUTOS LOGIN

    private GoogleApiClient googleApiClient;
    private String usuarioIdCuenta;
    private ImageView photoImageView;
    private Uri urlFoto;


    //Atributos TODOS LOS PARTIDOS
    ArrayList<Partido> todosPartidos = new ArrayList<>();
    ArrayList<String> todosPartidosRutasList = new ArrayList<>();
    String [] datosTodosPartidos;
    String p_todosPartidosRuta;
    Partido todosPartidosBajados;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);


        // CODIGO LOGIN

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();





        //yo = new Usuario(miNombre,7,usuarioIdCuenta);

        //partido1 =new Partido(yo,fecha_p1);


        //simulador.añadirMisPartidosSimulados(yo,misPartidos);

        // Firebase

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        //subirAFirebase(pPrueba,"Partidos/"+"Partidos de "+yo.getNombre()+"/");




    }

    public void IrMisPartidos(View v)  {

        setContentView(R.layout.activity_mispartidos2);
        LayoutActual="Mis Partidos";

        corregirMisPartidos();

        rutasMisPartidos();

        gestionarListView();


    }

    public void IrBuscarPartido(View v) throws InterruptedException {
        LayoutActual="Buscar Partido";

        String numero = "1";






        //bajarTodosPartidos(todosPartidosRutasList.get(2));


        //bajarRutaTodosPartidos();


        //String rutaBajada1=todosPartidosRutasList.get(1);

        //bajarTodosPartidos(rutaBajada);

        //sacarToast(rutaBajada,Toast.LENGTH_LONG);

       // Partido pNuevo = todosPartidos.get(0);

        //bajarTodosPartidos(rutaBajada);

        //bucleBajarTodosPartidos();

        //gestionarListViewTodosPartidos();

        if(todosPartidos.size()<numeroTodosPartidos){
            sacarToast("No esta cargado",Toast.LENGTH_SHORT);
            bucleBajarTodosPartidos();
            return;
        }

        setContentView(R.layout.activity_buscar_partido);
        gestionarListViewTodosPartidos();

    }




    public void IrValorar(View v){

        LayoutActual="Valorar";

        //setContentView(R.layout.activity_valoracion);


    }

    public void IrHome(View v){

        LayoutActual="Home";

        setContentView(R.layout.activity_main);

        TextView miNombreTextview = (TextView) findViewById(R.id.miNombreId);
        miNombreTextview.setText(miNombre);


    }

    public void IrPerfil(View v){


        LayoutActual="Perfil";

        setContentView(R.layout.activity_mi_perfil);


    }

    public void IrCrear(View v){


        LayoutActual="Perfil";

        setContentView(R.layout.activity_crear_partido);

        //dpResult.findViewById(R.id.datePickeId);




    }

    //////////////////////////
    //  METODOS DE MIS PARTIDOS
    ///////////////////////////

    public void gestionarListView(){


        //bucleBajarPartidos();
        corregirMisPartidos();

        bucleBajarPartidos();
        rellenarListView();



        listView = (ListView) findViewById(R.id.listId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show(); */


                setContentView(R.layout.activity_el_partido);
                TextView elPartidoTV = (TextView) findViewById(R.id.elPartidoId);
                String nombrePartido = datosRecortados(itemValue);
                elPartidoTV.setText(nombrePartido);
                ponerNombres(misPartidos.get(itemPosition));


            }

        });


    }

    public void bucleBajarPartidos(){
        for (int i=0;i<misPartidosRutasList.size();i++){
            bajarMiPartido(misPartidosRutasList.get(i));
        }

    }


    public void rutasMisPartidos(){

        String misPartidosString = yo.getMisPartidos();
        ArrayList<Integer> indicesCortar = new ArrayList<>();
        misPartidosRutasList.clear();

            String ruta ="";
            for(int i=0;i<misPartidosString.length();i++) {
                if(misPartidosString.charAt(i)=='&'){
                    indicesCortar.add(i);
                }
            }

            for(int i=1;i<indicesCortar.size();i++){

                ruta=misPartidosString.substring(indicesCortar.get(i-1)+1,indicesCortar.get(i));
                misPartidosRutasList.add(ruta);

            }



    }





    //////////////////////////
    //  METODOS CREAR PARTIDO
    ///////////////////////////


  /*  private DatePickerDialog.OnDateSetListener datePickerListener
            = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;

            // set selected date into textview
            String  fechaElegida =  ""+day+"/"+""+month+1+"/"+""+year;
            Toast.makeText(getApplicationContext(),
                    fechaElegida , Toast.LENGTH_LONG)
                    .show();

            // set selected date into datepicker also
            dpResult.init(year, month, day, null);

        }
    };
    */


    public void botonCrearPartido(View v){

        try {

            //  SE RECOGE LA FECHA

            dpResult = (DatePicker) findViewById(R.id.datePickeId);

            day = dpResult.getDayOfMonth();
            month = dpResult.getMonth() + 1;
            year = dpResult.getYear();


            //  SE RECOGE LA HORA

            EditText horaED = (EditText) findViewById(R.id.horaETid);
            hour = Integer.parseInt(horaED.getText().toString());
            EditText minED = (EditText) findViewById(R.id.minEDid);
            minute = Integer.parseInt(minED.getText().toString());

            if (horaPermitida()) {

                Toast.makeText(getApplicationContext(),
                        "Partido el " + getStringFecha("/"), Toast.LENGTH_LONG)
                        .show();

                Fecha fechaNueva = new Fecha();
                rellenarFecha(fechaNueva);
                //Creas el partido
                Partido partido_nuevo = new Partido(yo, fechaNueva);
                //Pones el id, que es fecha, hora y usuario
                partido_nuevo.setPartidoId(crearPertidoId());
                //misPartidos.add(partido_nuevo);

                // Firebase



                String partidos ="Partidos/";
                String partidosDe = "Partidos de "+yo.getUsuarioId()+"/";
                String partidoNuevo= partido_nuevo.getPartidoId();


                try {
                    subirAFirebase(yo.getMisPartidos(), "Usuarios/" + yo.getUsuarioId() + "/misPartidos");

                    subirAFirebase(partido_nuevo, partidos + partidosDe + partidoNuevo);

                    yo.setMisPartidos(yo.getMisPartidos()+partidoNuevo+"&");

                    subirAFirebase(yo,"Usuarios/"+yo.getUsuarioId());

                    addMisPartidos(partido_nuevo);

                    setContentView(R.layout.activity_mispartidos2);

                    gestionarListView();

                    addRutaTodosPartidos(partidos + partidosDe + partidoNuevo);

                }catch (Exception e){

                }

            } else{
                Toast.makeText(getApplicationContext(),"Hora no permitida", Toast.LENGTH_LONG)
                        .show();
            }
        }catch (Exception e){

            Toast.makeText(getApplicationContext(),"Pon una hora amigo", Toast.LENGTH_LONG)
                    .show();

        }

        //tmResult = (TimePicker) findViewById(R.id.timePickId);
        //hour = tmResult.getHour();
        //minute = tmResult.getMinute();


    }

    public String getStringFecha(String separador){

        return day+separador+month+separador+year;
    }

    public String getStringHora(){


        if(minute<10){

            return hour+" : 0"+minute;

        }


        return hour+" : "+minute;
    }


    public boolean horaPermitida(){

        boolean horaOk=false;
        boolean minOk=false;

        if(hour>0&&hour<24){
            horaOk=true;
        }
        if(minute>-1&&minute<60){
            minOk=true;
        }


        return horaOk&&minOk;
    }

    public void rellenarFecha(Fecha fechaNueva ){

        fechaNueva.setDay(day);
        fechaNueva.setMonth(month);
        fechaNueva.setHour(hour);
        fechaNueva.setYear(year);
        fechaNueva.setMinute(minute);




    }



    public void rellenarListView () {

        datos = new String[misPartidos.size()];

        for(int i=0;i<misPartidos.size();i++){


            day = misPartidos.get(i).getDate().getDay();
            month = misPartidos.get(i).getDate().getMonth();
            year = misPartidos.get(i).getDate().getYear();
            hour = misPartidos.get(i).getDate().getHour();
            minute = misPartidos.get(i).getDate().getMinute();

            datos[i]="El "+getStringFecha("/")+" a las "+getStringHora()+"\n"+getStringJugadoresQuedan(misPartidos.get(i));

        }
    }


    public String getStringJugadoresQuedan(Partido partido){

        int jugadoresRestantes = partido.jugadoresRestantes();

        if(jugadoresRestantes>1){
        return "Quedan "+jugadoresRestantes+" jugadores";}
        else{
            return "Queda "+jugadoresRestantes+" jugador";
        }
    }

    public String datosRecortados(String itemValue){

        int index = itemValue.indexOf('\n');


        return itemValue.substring(0,index);
    }



    //////////////////////////
    //  METODOS EL PARTIDO
    ///////////////////////////


    public void ponerNombres(Partido partidoElegido){

        int jugadoresRestantes = partidoElegido.jugadoresRestantes();


        TextView jugador1TV =(TextView) findViewById(R.id.jugador1Id);
        TextView jugador2TV =(TextView) findViewById(R.id.jugador2Id);
        TextView jugador3TV =(TextView) findViewById(R.id.jugador3Id);
        TextView jugador4TV =(TextView) findViewById(R.id.jugador4Id);



        String jugadorNull = "Añadir Jugador";

        String j1=jugadorNull;
        String j2=jugadorNull;
        String j3=jugadorNull;
        String j4=jugadorNull;
        if(jugadoresRestantes<4){
            j1= partidoElegido.getJugador1().getNombre();

        }if(jugadoresRestantes<3){
            j2= partidoElegido.getJugador2().getNombre();
        }if(jugadoresRestantes<2){
            j3=partidoElegido.getJugador3().getNombre();
        }if(jugadoresRestantes==0){
            j4=partidoElegido.getJugador4().getNombre();
        }


        jugador1TV.setText(j1);
        jugador2TV.setText(j2);
        jugador3TV.setText(j3);
        jugador4TV.setText(j4);
        if(j1==jugadorNull){
        jugador1TV.setTextColor(Color.RED);}
        if(j2==jugadorNull){
            jugador2TV.setTextColor(Color.RED);}
        if(j3==jugadorNull){
            jugador3TV.setTextColor(Color.RED);}
        if(j4==jugadorNull){
            jugador4TV.setTextColor(Color.RED);}

    }


    public void subirAFirebase(Object o, String ruta){


        myRef.child(ruta).setValue(o);

    }



    public String crearPertidoId(){


        String IdCreado = getStringFecha(" ")+" "+getStringHora()+"-"+yo.getUsuarioId();
        return IdCreado;
    }

    // METODOS LOGIN

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        OptionalPendingResult<GoogleSignInResult> opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if (opr.isDone()) {
            GoogleSignInResult result = opr.get();
            handleSignInResult(result);
        } else {
            opr.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(@NonNull GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }
    }
    //Esta es la clave :)
    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {

            GoogleSignInAccount account = result.getSignInAccount();

            miNombre = account.getDisplayName();

            usuarioIdCuenta=account.getId();

            // Esto es lo decisivo
            //TODO importante bajar usuario!!!!

            bajarUsuario(usuarioIdCuenta);

            bajarNumeroTodosPartidos();

            //bajarStringPrueba(0);

            //bucleBajarTodosPartidos();




            //bucleBajarTodosPartidos();

            //bajarStringPrueba(0);

            //bajarTodosPartidos("Partidos/Partidos de 109439928285176193541/10 4 2017 10 : 00-109439928285176193541");

            //yo = new Usuario(miNombre,7,usuarioIdCuenta);
            //subirAFirebase(yo,"Usuarios/"+yo.getUsuarioId());


            //simulador.añadirMisPartidosSimulados(yo,misPartidos);

            //nameTextView.setText(account.getDisplayName());
            //emailTextView.setText(account.getEmail());
            //idTextView.setText(account.getId());

            urlFoto = account.getPhotoUrl();






        } else {
            goLogInScreen();
        }
    }

    private void goLogInScreen() {
        Intent intent = new Intent(this, LogInActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    public void logOut(View view) {
        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "session not closed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void revoke(View view) {
        Auth.GoogleSignInApi.revokeAccess(googleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                if (status.isSuccess()) {
                    goLogInScreen();
                } else {
                    Toast.makeText(getApplicationContext(), "note revoked", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void bajarUsuario(String usuarioIdCuentaYo){

        DatabaseReference myRefUsuarios = database.getReference("Usuarios/"+usuarioIdCuentaYo);

        myRefUsuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                yo = dataSnapshot.getValue(Usuario.class);

                if(yo==null){

                    yo = new Usuario(miNombre,7,usuarioIdCuenta);
                    subirAFirebase(yo,"Usuarios/"+yo.getUsuarioId());

                }



                rutasMisPartidos();

                bucleBajarPartidos();

                bucleBajarTodosPartidos();


                rellenarPerfil();





                //Log.d(TAG, "Value is: " + value);
                Log.d("bajar usuario","bien");
                //Toast.makeText(getApplicationContext(), "Descarga usuario completada", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("bajar usuario", "Failed to read value.", error.toException());
                yo = new Usuario(miNombre,7,usuarioIdCuenta);
                subirAFirebase(yo,"Usuarios/"+yo.getUsuarioId());
                //Toast.makeText(getApplicationContext(), "Fallo al bajar usuario", Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void rellenarPerfil(){
        try {
            setContentView(R.layout.activity_main);
            TextView miNombreTextview = (TextView) findViewById(R.id.miNombreId);
            miNombreTextview.setText(yo.getNombre());

            //TODO Aquí se decide la foto
            photoImageView=(ImageView) findViewById(R.id.miFotoId);

            try{
                if(urlFoto.isOpaque()){ //Si no tiene foto da error y se mete en el catch

                    Glide.with(this).load(urlFoto).into(photoImageView);
                }
                else{
                    // No debería entrar nunca aquí
                }
            }catch (Exception e){
                sacarToast("Sin foto",Toast.LENGTH_LONG);
                //No hace nada, así que deja la foto anterior

            }


        }
        catch (Exception e){

            sacarToast("Fallo al rellenar perfil",Toast.LENGTH_SHORT);

        }

    }

    public void sacarToast(String mensaje,int longitud){

        Toast.makeText(getApplicationContext(), mensaje, longitud).show();

    }

    public void bajarMiPartido(String rutaStringPartido){

        DatabaseReference myRefMiPartido = database.getReference("Partidos/Partidos de "+yo.getUsuarioId()+"/"+rutaStringPartido);



        myRefMiPartido.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Partido miPartido = dataSnapshot.getValue(Partido.class);

                if(misPartidos.contains(miPartido)==false){

               // misPartidos.add(miPartido);
                    //TODO añadir partido al bajartelo
                addMisPartidos(miPartido);

                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                
            }


        });




    }



    public void corregirMisPartidos(){

        for(int i=0;i<misPartidos.size();i++){
            for(int j=0;j<misPartidos.size();j++){
                if(i==j){

                }else{

                    String id1=misPartidos.get(i).getPartidoId();
                    String id2=misPartidos.get(j).getPartidoId();
                    boolean condicion = id1.equals(id2);
                    //misPartidos.get(i).getPartidoId()==misPartidos.get(j).getPartidoId()
                    if(condicion){
                        misPartidos.remove(j);
                        j--;
                    }
                }
            }
        }

    }
    //Metodo que gestiona el añadido de partidos
    public void addMisPartidos(Partido p_nuevo){

        if(misPartidos.size()==0){
            misPartidos.add(p_nuevo);

        }else{
            boolean added = false;

            for(int i=0;i<misPartidos.size();i++){

                String id1 = misPartidos.get(i).getPartidoId();
                String id2 = p_nuevo.getPartidoId();
                if(id1.equals(id2)){
                    misPartidos.remove(i);
                    misPartidos.add(i,p_nuevo);
                    added = true;
                }

            }
            if(added==false){
                misPartidos.add(p_nuevo);
            }

        }

    }

    public void addRutaTodosPartidos(String ruta){

        numeroTodosPartidos++;
        subirAFirebase(ruta,"Todos Partidos/"+numeroTodosPartidos);
        subirAFirebase(numeroTodosPartidos,"Todos Partidos/Numero");


    }

//METODOS PARA BAJAR TODOS PARTIDOS


    public void bajarNumeroTodosPartidos() {

        DatabaseReference myRefNumeroTodosPartidos = database.getReference("Todos Partidos/Numero");


        myRefNumeroTodosPartidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                numeroTodosPartidos = dataSnapshot.getValue(int.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }


        });


    }


    public void bajarStringPrueba(int ruta) {

        DatabaseReference myRefNumeroTodosPartidos = database.getReference("Todos Partidos/"+ruta);


        myRefNumeroTodosPartidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String bajado = dataSnapshot.getValue(String.class);
                //Cada vez que se baja una ruta, se baja el partido y lo añade
                todosPartidosRutasList.add(bajado);
                bajarTodosPartidos(bajado);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }


        });


    }


    /*public void bajarRutaTodosPartidos() {

        DatabaseReference myRefNumeroTodosPartidos = database.getReference("Todos Partidos/0");


        myRefNumeroTodosPartidos.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                p_todosPartidosRuta = dataSnapshot.getValue(String.class);


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }


        });


    }
*/

    public void bajarTodosPartidos(String ruta) {

        DatabaseReference myRefTodosPartidosPrueba = database.getReference(ruta);


        myRefTodosPartidosPrueba.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                todosPartidosBajados = dataSnapshot.getValue(Partido.class);
                addTodosPartidos(todosPartidosBajados);
                //todosPartidos.add(todosPartidosBajados);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }


        });


    }


    public void bucleBajarTodosPartidos(){

        for(int i=0;i<numeroTodosPartidos+1;i++){
            bajarStringPrueba(i);

            //bajarTodosPartidos(todosPartidosRutasList.get(i));
        }


    }

    //Metodo que gestiona el añadido de todosPartidos
    public void addTodosPartidos(Partido p_nuevo){

        if(todosPartidos.size()==0){
            todosPartidos.add(p_nuevo);

        }else{
            boolean added = false;

            for(int i=0;i<todosPartidos.size();i++){

                String id1 = todosPartidos.get(i).getPartidoId();
                String id2 = p_nuevo.getPartidoId();
                if(id1.equals(id2)){
                    todosPartidos.remove(i);
                    todosPartidos.add(i,p_nuevo);
                    added = true;
                }

            }
            if(added==false){
                todosPartidos.add(p_nuevo);
            }

        }

    }

    public void borrarMisPartidos(){
        yo.setMisPartidos("&");
        subirAFirebase(yo,"Usuarios/"+yo.getUsuarioId());
    }

    public void gestionarListViewTodosPartidos(){

        rellenarListViewTodosPartidos();



        listView = (ListView) findViewById(R.id.listIdTodosPartidos);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datosTodosPartidos);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                /*Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show(); */


                setContentView(R.layout.activity_el_partido);
                TextView elPartidoTV = (TextView) findViewById(R.id.elPartidoId);
                String nombrePartido = datosTodosPartidosRecortados(itemValue);
                elPartidoTV.setText(nombrePartido);
                ponerNombres(todosPartidos.get(itemPosition));


            }

        });


    }
    public String datosTodosPartidosRecortados(String itemValue){

        int index = itemValue.indexOf('\n');


        return itemValue.substring(0,index);
    }


    public void rellenarListViewTodosPartidos () {

        datosTodosPartidos = new String[todosPartidos.size()];

        for(int i=0;i<todosPartidos.size();i++){


            day = todosPartidos.get(i).getDate().getDay();
            month = todosPartidos.get(i).getDate().getMonth();
            year = todosPartidos.get(i).getDate().getYear();
            hour = todosPartidos.get(i).getDate().getHour();
            minute = todosPartidos.get(i).getDate().getMinute();

            datosTodosPartidos[i]="El "+getStringFecha("/")+" a las "+getStringHora()+"\n"+getStringJugadoresQuedan(todosPartidos.get(i));

        }
    }




}