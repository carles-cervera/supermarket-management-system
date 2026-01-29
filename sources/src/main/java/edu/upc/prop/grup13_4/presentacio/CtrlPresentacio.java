package edu.upc.prop.grup13_4.presentacio;

import edu.upc.prop.grup13_4.domini.CtrlDomini;
import edu.upc.prop.grup13_4.presentacio.views.WelcomePanel;

import javax.swing.*;
import java.awt.*;
import java.util.List;
/**
 * @brief Gestiona la interacció i vistes de l'aplicació.
 */
public class CtrlPresentacio {

    //Visual elements
    private static CtrlPresentacio instance;
    private final CtrlDomini cDomini = CtrlDomini.getInstance();
    public MainFrame mainFrame;
    private JFrame logInFrame;
    private CardLayout cardLayout;
    private JPanel dynamicLogInPanel;
    private JFrame crearDistribucioFrame;

    //Instances of ViewControllers
    private CrearInventariViewController crearInventariVC;
    private InventariViewController inventariVC;
    private ConfiguracioPerfilViewController configuracioPerfilVC;
    private MenuDistribucionsViewController menuDistribucionsVC;
    private DistribucioViewControler distribucioVC;
    private LogInViewController logInVC;
    private SignUpViewController signUpVC;
    private CreateDistribucioViewController createDistVC;
    private MenuInventariViewController menuInventariVC;
    private PrincipalViewController principalVC;
    private ProducteIndividualViewController producteIndividualVC;
    private WelcomePanel welcomeView;
    private ScriptDialogViewController scriptDialogVC;

    private CtrlPresentacio() {


    }

    /**
     * @brief Inicia el MainFrame, on es veurà tota l'aplicació.
     *
     */
    public void init() {
        principalVC = new PrincipalViewController();
        mainFrame = new MainFrame(principalVC.getPanel());

        //Inicialització Frame inicial
        logInFrame = new JFrame();
        logInFrame.setTitle("FreshMart");
        logInFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        logInFrame.setSize(900, 450);
        logInFrame.setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        dynamicLogInPanel = new JPanel(cardLayout);
        logInFrame.add(dynamicLogInPanel);
        logInFrame.setResizable(false);

        ImageIcon icono = new ImageIcon(getClass().getResource("/presentacio/imatges/logo.png"));
        logInFrame.setIconImage(icono.getImage());

        //View controller initialization
        crearInventariVC = new CrearInventariViewController();
        createDistVC = CreateDistribucioViewController.getInstance();
        inventariVC = new InventariViewController();
        configuracioPerfilVC = new ConfiguracioPerfilViewController();
        menuDistribucionsVC = new MenuDistribucionsViewController();
        distribucioVC = new DistribucioViewControler();
        logInVC = LogInViewController.getInstance();
        signUpVC = SignUpViewController.getInstance();
        menuInventariVC = MenuInventariViewController.getInstance();
        welcomeView = new WelcomePanel();
        producteIndividualVC = ProducteIndividualViewController.getInstance();
        scriptDialogVC = ScriptDialogViewController.getInstance();

        //Give panels to MainFrame
        mainFrame.dynamicPanel.add("perfil", configuracioPerfilVC.getConfiguracioPerfilView());
        mainFrame.dynamicPanel.add("menuDistribucions", menuDistribucionsVC.getmenuDistribucionsView());
        mainFrame.dynamicPanel.add("crearInventari", crearInventariVC.getCrearInventariView());
        mainFrame.dynamicPanel.add("distribucio", distribucioVC.getDistribucioView());
        mainFrame.dynamicPanel.add("menuInventaris", menuInventariVC.getmenuInventarisView() );
        mainFrame.dynamicPanel.add("inventari", inventariVC.getView());
        mainFrame.dynamicPanel.add("welcome",welcomeView);
        //mainFrame.dynamicPanel.add("crearInventari", configuracioPerfilVC.getConfiguracioPerfilView());

        //Instanciar frame CrearDistribucio
        crearDistribucioFrame = createDistVC.getCreateDistribucioView();
        //Give panels to initial Frame
        dynamicLogInPanel.add("logIn", logInVC.getLogInView());
        dynamicLogInPanel.add("signUp", signUpVC.getSignUpView());

        mainFrame.init();

        //Show Login
        cardLayout.show(dynamicLogInPanel, "logIn");
        logInFrame.setVisible(true);

    }

    public static CtrlPresentacio getInstance() {
        if (instance == null) {
            instance = new CtrlPresentacio();
        }
        return instance;
    }

    /**
     * @brief Navegabilitat al menú d'inventaris.
     */
    public void goToMenuInventari() {
        cDomini.unselectInventari();
        menuInventariVC.recargapanel();
        menuInventariVC.recargapreviewbuida();
        mainFrame.showMenuCL("menuInventaris");
    }

    /**
     * @brief Navegabilitat al menú de benvinguda.
     */
    public void goToWelcome() {
        mainFrame.showMenuCL("welcome");
    }

    /**
     * @brief Navegabilitat al menú de perfil.
     */
    public void goToPerfil() {
        configuracioPerfilVC.reloadConfiguracioPerfil();
        mainFrame.showMenuCL("perfil");
    }

    /**
     * @brief Navegabilitat al menú de distribucions.
     */
    public void goToMenuDistribucions() {
        menuDistribucionsVC.reloadMenuDistribucions();
        mainFrame.showMenuCL("menuDistribucions");
    }

    /**
     * @brief Navegabilitat a la vista de crear inventari.
     */
    public void goToCrearInventari() {
        if (crearInventariVC.dynamicUpdate()) mainFrame.showMenuCL("crearInventari");
        else goToMenuInventari();
    }

    /**
     * @brief Navegabilitat a la vista d'inventari individual.
     */
    public void goToInventariIndividual() {
        inventariVC.reloadInventariPanel();
        mainFrame.showMenuCL("inventari");
    }

    /**
     * @brief Navegabilitat a la vista de crear distribució.
     */
    public void goToCrearDistribucio() {
        createDistVC.reloadCreateDistribucioFrame();
        crearDistribucioFrame.setVisible(true);
    }

    /**
     * @brief Navegabilitat a la vista de distribució individual.
     */
    public void goToDitribucio() {
        distribucioVC.reloadDistribucioView();
        mainFrame.showMenuCL("distribucio");
    }

    /**
     * @brief Cas d'ús d'afegir producte.
     */
    public void afegirProducte(String id) {
        cDomini.afegirProducte(id);
    }

    /**
     * @brief Deseleccionar una distribució prèviament seleccionada.
     */
    public void unselectDistribucio() {
        cDomini.unselectDistribucio();
        menuDistribucionsVC.reloadMenuDistribucions();

    }

    /**
     * @brief Retorna la distribució demanada.
     */
    public List<List<String>> getDistribucio(String id) {
        return cDomini.getDistribucio(id);
    }

    /**
     * @brief Retorna les distirbucions d'un usuari.
     */
    public String[] getIdDistribucions() {
        return cDomini.getIdDistribucions();
    }


    /**
     * @brief Selecciona una distribució especificada.
     */
    public void selectDistribucio(String idDistribucio) {
        cDomini.selectDistribucio(idDistribucio);
    }

    /**
     * @brief Retorna la distribució actual.
     */
    public List<List<String>> getActiveDistribution() {
        return cDomini.getActiveDistribution();
    }

    /**
     * @brief Cas d'ús d'esborrar producte.
     */
    public void deleteProducte(String idProducte) {
        cDomini.deleteProducte(idProducte);
    }

    /**
     * @brief Cas d'ús de duplicar distribució.
     */
    public void duplicarDistribucio(String newidProducte) {
        cDomini.duplicarDistribucio(newidProducte);
    }

    /**
     * @brief Cas d'ús de moure un producte.
     */
    public void canviarDeLlocProducte(String producte, int x, int y) {
        cDomini.canviarDeLlocProducte(producte, x, y);
    }

    /**
     * @brief Reanomenar distribució.
     */
    public void changeIdDistribucio(String newIdDistribucio) {
        cDomini.changeIdDistribucio(newIdDistribucio);
    }

    /**
     * @brief Cas d'ús d'esborrar distribució.
     */
    public void deleteDistribucio(String id) {
        cDomini.deleteDistribucio(id);
        menuDistribucionsVC.reloadMenuDistribucions();
    }

    /**
     * @brief Retorna la distribució actual.
     */
    public String getIdActiveDistribution() {
        return cDomini.getIdActiveDistribution();
    }
    public void sigOut() {
        cDomini.signOut();
        mainFrame.setVisible(false);
        logInFrame.setVisible(true);
        goToLogInPanel();
    }

    /**
     * @brief Retorna el nom real de l'usuari.
     */
    public String getRealNameActiveProfile() {
        return cDomini.getRealNameActiveProfile();
    }

    /**
     * @brief Retorna el username de l'usuari.
     */
    public String getUsernameActiveProfile() {
        return cDomini.getUsernameActiveProfile();
    }

    /**
     * @brief Retorna l apassword de l'usuari.
     */
    public String remindPassword() {
        return cDomini.remindPassword();
    }

    /**
     * @brief Canvia la password.
     */
    public void changePassword(String contrasenyaActual, String contrasenyaNova) {
        cDomini.changePassword(contrasenyaActual,contrasenyaNova);
    }

    /**
     * @brief Cas d'ús d'esborrar perfil.
     */
    public void deletePerfil() {
        cDomini.deletePerfil();
        mainFrame.setVisible(false);
        logInFrame.setVisible(true);
        goToLogInPanel();
    }

    /**
     * @brief Navegabilitat a la vista de distribució.
     */
    public void changeADistribucio(String id) {
        selectDistribucio(id);
        distribucioVC.reloadDistribucioView();
        //showPanel("distrib");
    }

    /**
     * @brief Cas d'ús de login.
     */
    public void logIn(String username, String password) {
        cDomini.logIn(username, password);
    }

    /**
     * @brief Cas d'ús de signup.
     */
    public void signUp(String realname, String username, String password) {
        cDomini.signUp(realname, username, password);
    }

    /**
     * @brief Cas d'ús de crear distribució.
     */
    public void createDistribucio(String idDistribucio, boolean importat, String mode, int numPrestatges) {
        cDomini.createDistribucio(idDistribucio,importat,mode,numPrestatges);
    }

    /**
     * @brief Selecciona un invnetari.
     */
    public void seleccionarInventari(String id) {
        cDomini.seleccionarInventari(id);
    }

    /**
     * @brief Retorna les relacions d'un producte.
     */
    public List<String> getInfoProductes() {
        return cDomini.getInfoProductes();
    }

    /**
     * @brief Retorna els ids dels inventaris d'un user.
     */
    public List<String> getInventarisIds() {
        return cDomini.getInventarisIds();
    }

    /**
     * @brief Navegabilitat a la vista de signup.
     */
    public void goToSignUpPanel() {
        signUpVC.reloadSignUpView();
        cardLayout.show(dynamicLogInPanel, "signUp");

    }

    /**
     * @brief Navegabilitat a la vista de login.
     */
    public void goToLogInPanel() {
        logInVC.reloadLogInView();
        cardLayout.show(dynamicLogInPanel,"logIn");
    }

    /**
     * @brief Navegabilitat a la vista de benvinguda.
     */
    public void goToPrincipalPanel() {
        logInFrame.setVisible(false);
        mainFrame.setVisible(true);
        mainFrame.showMenuCL("welcome");
    }

    /**
     * @brief Save inventari.
     */
    public void guardarInventari() {
        cDomini.guardarInventari();
    }

    /**
     * @brief Treu la finestra de producte individual.
     */
    public void popProducte(String id) {
        producteIndividualVC.setPopup(id);
    }

    /**
     * @brief Permet l'ús de scripts amb dades.
     */
    public void setScriptDialog() {
        scriptDialogVC.setScriptDialog();
    }

    /**
     * @brief Retorna l'id de l'inventari actual.
     */
    public String getIdActiveInventari() {
        return cDomini.getIDActiveInventari();
    }

    /**
     * @brief Cas d'ús d'esborrar inventari.
     */
    public void deleteInventari() {
        cDomini.eliminarInventari();
        goToMenuInventari();
    }

    /**
     * @brief Cas d'ús d'importar distribució.
     */
    public void importarDistribucio(String id, List<List<String>> productes) {
        cDomini.importarDistribucio(id, productes);
    }

    /**
     * @brief Retorna el número de productes d'un inventari.
     */
    public Integer getNumProductes() {
        return cDomini.getNumProductes();
    }

}

