package com.mikwee.barcodezxingembedded.entities;

import android.text.TextUtils;
import android.util.Log;

import com.mikwee.barcodezxingembedded.R;

public class BarcodeInfo {
    private final String TAG = BarcodeInfo.class.getSimpleName();

    private String barcode;
    private int flagRes = -1;
    private String country;
    private String firstThree;


    public BarcodeInfo(String code) {

        if (code == null)
            return;

        this.barcode = code;

        try {
            this.firstThree = code.substring(0, 3);
        }catch (IndexOutOfBoundsException e){
            Log.e(TAG, "Barcode must be at least long 3");
        }

        //Set country name and flag
        countryLogics(firstThree);
    }

    //todo rethink this method da capo
    private void countryLogics(String c) {


        switch (c) {
            case "000":
            case "001":
            case "002":
            case "003":
            case "004":
            case "005":
            case "006":
            case "007":
            case "008":
            case "009":
            case "010":
            case "011":
            case "012":
            case "013":
            case "014":
            case "015":
            case "016":
            case "017":
            case "018":
            case "019":
            case "020":
            case "021":
            case "022":
            case "023":
            case "024":
            case "025":
            case "026":
            case "027":
            case "028":
            case "029":
            case "030":
            case "031":
            case "032":
            case "033":
            case "034":
            case "035":
            case "036":
            case "037":
            case "038":
            case "039":
            case "040":
            case "041":
            case "042":
            case "043":
            case "044":
            case "045":
            case "046":
            case "047":
            case "048":
            case "049":
            case "050":
            case "051":
            case "052":
            case "053":
            case "054":
            case "055":
            case "056":
            case "057":
            case "058":
            case "059":
            case "060":
            case "061":
            case "062":
            case "063":
            case "064":
            case "065":
            case "066":
            case "067":
            case "068":
            case "069":
            case "070":
            case "071":
            case "072":
            case "073":
            case "074":
            case "075":
            case "076":
            case "077":
            case "078":
            case "079":
            case "080":
            case "081":
            case "082":
            case "083":
            case "084":
            case "085":
            case "086":
            case "087":
            case "088":
            case "089":
            case "090":
            case "091":
            case "092":
            case "093":
            case "094":
            case "095":
            case "096":
            case "097":
            case "098":
            case "099":
            case "100":
            case "101":
            case "102":
            case "103":
            case "104":
            case "105":
            case "106":
            case "107":
            case "108":
            case "109":
            case "110":
            case "111":
            case "112":
            case "113":
            case "114":
            case "115":
            case "116":
            case "117":
            case "118":
            case "119":
            case "120":
            case "121":
            case "122":
            case "123":
            case "124":
            case "125":
            case "126":
            case "127":
            case "128":
            case "129":
            case "130":
            case "131":
            case "132":
            case "133":
            case "134":
            case "135":
            case "136":
            case "137":
            case "138":
            case "139":

                flagRes = R.drawable.us;
                country = "USA";

                break;
            case "300":
            case "301":
            case "302":
            case "303":
            case "304":
            case "305":
            case "306":
            case "307":
            case "308":
            case "309":
            case "310":
            case "311":
            case "312":
            case "313":
            case "314":
            case "315":
            case "316":
            case "317":
            case "318":
            case "319":
            case "320":
            case "321":
            case "322":
            case "323":
            case "324":
            case "325":
            case "326":
            case "327":
            case "328":
            case "329":
            case "330":
            case "331":
            case "332":
            case "333":
            case "334":
            case "335":
            case "336":
            case "337":
            case "338":
            case "339":
            case "340":
            case "341":
            case "342":
            case "343":
            case "344":
            case "345":
            case "346":
            case "347":
            case "348":
            case "349":
            case "350":
            case "351":
            case "352":
            case "353":
            case "354":
            case "355":
            case "356":
            case "357":
            case "358":
            case "359":
            case "360":
            case "361":
            case "362":
            case "363":
            case "364":
            case "365":
            case "366":
            case "367":
            case "368":
            case "369":
            case "370":
            case "371":
            case "372":
            case "373":
            case "374":
            case "375":
            case "376":
            case "377":
            case "378":
            case "379":

                flagRes = R.drawable.fr;
                country = "France";

                break;
            case "380":

                flagRes = R.drawable.bg;
                country = "Bulgarie";

                break;
            case "383":

                flagRes = R.drawable.sl;
                country = "Slovenia";

                break;
            case "385":

                flagRes = R.drawable.hr;
                country = "Croitie";

                break;
            case "387":

                flagRes = R.drawable.ba;

                break;
            case "389":

                flagRes = R.drawable.me;

                break;
            case "390":

                flagRes = R.drawable.ks;

                break;
            case "400":
            case "401":
            case "402":
            case "403":
            case "404":
            case "405":
            case "406":
            case "407":
            case "408":
            case "409":
            case "410":
            case "411":
            case "412":
            case "413":
            case "414":
            case "415":
            case "416":
            case "417":
            case "418":
            case "419":
            case "420":
            case "421":
            case "422":
            case "423":
            case "424":
            case "425":
            case "426":
            case "427":
            case "428":
            case "429":
            case "430":
            case "431":
            case "432":
            case "433":
            case "434":
            case "435":
            case "436":
            case "437":
            case "438":
            case "439":
            case "440":

                flagRes = R.drawable.de;
                country = "Germany";

                break;
            case "450":
            case "451":
            case "452":
            case "453":
            case "454":
            case "455":
            case "456":
            case "457":
            case "458":
            case "459":

                flagRes = R.drawable.jp;
                country = "Japan";

                break;
            case "460":
            case "461":
            case "462":
            case "463":
            case "464":
            case "465":
            case "466":
            case "467":
            case "468":
            case "469":

                flagRes = R.drawable.ru;
                country = "Russia";

                break;
            case "470":

                flagRes = R.drawable.kg;

                break;
            case "471":

                flagRes = R.drawable.tw;

                break;
            case "474":

                flagRes = R.drawable.ee;

                break;
            case "475":

                flagRes = R.drawable.lv;
                country = "Latvia";

                break;
            case "476":

                flagRes = R.drawable.az;
                country = "Azerbaijan";

                break;
            case "477":

                flagRes = R.drawable.lt;
                country = "Lituanie";

                break;
            case "478":

                flagRes = R.drawable.uz;
                country = "Uzbekistan";

                break;
            case "479":

                flagRes = R.drawable.lk;
                country = "";

                break;
            case "480":

                flagRes = R.drawable.ph;

                break;
            case "481":

                flagRes = R.drawable.by;
                country = "Belarus";

                break;
            case "482":

                flagRes = R.drawable.ua;
                country = "Ukraine";

                break;
            case "483":

                flagRes = R.drawable.tm;

                break;
            case "484":

                flagRes = R.drawable.md;
                country = "Moldova";

                break;
            case "485":

                flagRes = R.drawable.am;
                country = "Armenie";

                break;
            case "486":

                flagRes = R.drawable.ge;

                break;
            case "487":
                flagRes = R.drawable.kz;
                country = "Kazakhstan";
                break;
            case "488":
                flagRes = R.drawable.tj;
                country = "Tadjakistan";
                break;
            case "489":
                flagRes = R.drawable.hk;
                break;
            case "490":
            case "491":
            case "492":
            case "493":
            case "494":
            case "495":
            case "496":
            case "497":
            case "498":
            case "499":
                flagRes = R.drawable.jp;
                country = "Japan";
                break;
            case "500":
            case "501":
            case "502":
            case "503":
            case "504":
            case "505":
            case "506":
            case "507":
            case "508":
            case "509":
                flagRes = R.drawable.gb;
                country = "Great Britain";
                break;
            case "520":
            case "521":
                flagRes = R.drawable.gr;
                country = "Greece";
                break;
            case "528":
                flagRes = R.drawable.lb;
                country = "Liban";
                break;
            case "529":
                flagRes = R.drawable.cy;
                break;
            case "530":
                flagRes = R.drawable.al;
                country = "Albanie";
                break;
            case "531":
                flagRes = R.drawable.mk;
                country = "Macedonia";
                break;
            case "535":
                flagRes = R.drawable.mt;
                break;
            case "539":
                flagRes = R.drawable.ie;
                country = "Republic of Ireland";
                break;
            case "540":
            case "541":
            case "542":
            case "543":
            case "544":
            case "545":
            case "546":
            case "547":
            case "548":
            case "549":
                flagRes = R.drawable.be;
                country = "Belgique";
                break;
            case "560":
                flagRes = R.drawable.pt;
                country = "Portugal";
                break;
            case "569":
                flagRes = R.drawable.is;
                country = "Island";
                break;
            case "570":
            case "571":
            case "572":
            case "573":
            case "574":
            case "575":
            case "576":
            case "577":
            case "578":
            case "579":
                flagRes = R.drawable.dk;
                country = "Danemark";
                break;
            case "590":
                flagRes = R.drawable.pl;
                country = "Pologne";
                break;
            case "594":
                flagRes = R.drawable.ro;
                country = "Romania";
                break;
            case "599":
                flagRes = R.drawable.hu;
                country = "Hungaria";
                break;
            case "600":
            case "601":
                flagRes = R.drawable.za;
                country = "South Africa";
                break;
            case "603":
                flagRes = R.drawable.gh;
                country = "Gana";
                break;
            case "604":
                flagRes = R.drawable.sn;
                country = "Senegal";
                break;
            case "608":
                flagRes = R.drawable.bh;
                country = "Bahrain";
                break;
            case "609":
                flagRes = R.drawable.mu;
                break;
            case "611":
                flagRes = R.drawable.ma;
                country = "Maroc";
                break;
            case "613":
                flagRes = R.drawable.dz;
                country = "Algeria";
                break;
            case "615":
                flagRes = R.drawable.ng;
                country = "Nigeria";
                break;
            case "616":
                flagRes = R.drawable.ke;
                country = "Kenia";
                break;
            case "618":

                flagRes = R.drawable.ie;
                country = "Ireland";
                break;
            case "619":
                flagRes = R.drawable.tn;
                country = "Tunisia";
                break;
            case "620":
                flagRes = R.drawable.tz;
                country = "Tanzanie";
                break;
            case "621":
                flagRes = R.drawable.sy;
                country = "Syria";
                break;
            case "622":
                flagRes = R.drawable.eg;
                country = "Egypt";
                break;
            case "623":
                flagRes = R.drawable.bn;
                country = "Brunei";
                break;
            case "624":
                flagRes = R.drawable.ly;
                country = "Lybia";
                break;
            case "625":
                flagRes = R.drawable.jo;
                country = "Jordan";
                break;
            case "626":
                flagRes = R.drawable.ir;
                country = "Iran";
                break;
            case "627":
                flagRes = R.drawable.kw;
                country = "Kuwait";
                break;
            case "628":
                flagRes = R.drawable.sa;
                country = "Saudie Arabia";
                break;
            case "629":
                flagRes = R.drawable.ae;
                country = "United Arab Emirates";
                break;
            case "640":
            case "641":
            case "642":
            case "643":
            case "644":
            case "645":
            case "646":
            case "647":
            case "648":
            case "649":
                flagRes = R.drawable.fi;
                country = "Finland";
                break;
            case "690":
            case "691":
            case "692":
            case "693":
            case "694":
            case "695":
            case "696":
            case "697":
            case "698":
            case "699":
                flagRes = R.drawable.cn;
                country = "China";
                break;
            case "700":
            case "701":
            case "702":
            case "703":
            case "704":
            case "705":
            case "706":
            case "707":
            case "708":
            case "709":
                flagRes = R.drawable.no;
                country = "Norway";
                break;
            case "729":
                flagRes = R.drawable.il;
                country = "Israel";
                break;
            case "730":
            case "731":
            case "732":
            case "733":
            case "734":
            case "735":
            case "736":
            case "738":
            case "739":
                flagRes = R.drawable.se;
                country = "Sweden";
                break;
            case "740":
                flagRes = R.drawable.gt;
                break;
            case "741":
                flagRes = R.drawable.sv;
                break;
            case "742":
                flagRes = R.drawable.hn;
                break;
            case "743":
                flagRes = R.drawable.ni;
                country = "Nicaragua";
                break;
            case "744":
                flagRes = R.drawable.cr;
                country = "Costa Rica";
                break;
            case "745":
                flagRes = R.drawable.pa;
                country = "Panama";
                break;
            case "746":
                flagRes = R.drawable.dom;
                break;
            case "750":
                flagRes = R.drawable.mx;
                country = "Mexico";
                break;
            case "754":
                flagRes = R.drawable.ca;
                country = "Canada";
                break;
            case "755":
                flagRes = R.drawable.ca;
                country = "Canada";
                break;
            case "759":
                flagRes = R.drawable.ve;
                country = "Venezuela";
                break;
            case "760":
            case "761":
            case "762":
            case "763":
            case "764":
            case "765":
            case "766":
            case "767":
            case "768":
            case "769":
                flagRes = R.drawable.ch;
                country = "Swiss";
                break;
            case "770":
            case "771":
                flagRes = R.drawable.co;
                country = "Colombia";
                break;
            case "773":
                flagRes = R.drawable.uy;
                country = "Uruguay";
                break;
            case "775":
                flagRes = R.drawable.pe;
                country = "Peru";
                break;
            case "777":
                flagRes = R.drawable.bo;
                country = "";
                break;
            case "778":
                flagRes = R.drawable.ar;
                country = "Argentina";
                break;
            case "779":
                flagRes = R.drawable.ar;
                break;
            case "780":
                flagRes = R.drawable.cl;
                country = "Chili";
                break;
            case "784":
                flagRes = R.drawable.py;
                country = "Paraguay";
                break;
            case "786":
                flagRes = R.drawable.ec;
                country = "Ecuador";
                break;
            case "789":
                flagRes = R.drawable.br;
                country = "Brasil";
                break;
            case "790":
                flagRes = R.drawable.br;
                country = "Brasil";
                break;
            case "800":
            case "801":
            case "802":
            case "803":
            case "804":
            case "805":
            case "806":
            case "807":
            case "808":
            case "809":
            case "810":
            case "811":
            case "812":
            case "813":
            case "814":
            case "815":
            case "816":
            case "817":
            case "818":
            case "819":
            case "820":
            case "821":
            case "822":
            case "823":
            case "824":
            case "825":
            case "826":
            case "827":
            case "828":
            case "829":
            case "830":
            case "831":
            case "832":
            case "833":
            case "834":
            case "835":
            case "836":
            case "837":
            case "838":
            case "839":
                flagRes = R.drawable.it;
                country = "Italy";
                break;
            case "840":
            case "841":
            case "842":
            case "843":
            case "844":
            case "845":
            case "846":
            case "847":
            case "848":
            case "849":
                flagRes = R.drawable.es;
                country = "Espagne";
                break;
            case "850":
                flagRes = R.drawable.cu;
                country = "Cuba";
                break;
            case "858":
                flagRes = R.drawable.sk;
                country = "Slovakia";
                break;
            case "859":
                flagRes = R.drawable.cz;
                country = "Česká republika";
                break;
            case "860":
                flagRes = R.drawable.ser;
                country = "Serbia";
                break;
            case "865":
                flagRes = R.drawable.mn;
                break;
            case "867":
                flagRes = R.drawable.kp;
                country = "North Korea";
                break;
            case "868":
                flagRes = R.drawable.tr;
                country = "Turkey";
                break;
            case "869":
                flagRes = R.drawable.tr;
                country = "Turkey";
                break;
            case "870":
            case "871":
            case "872":
            case "873":
            case "874":
            case "875":
            case "876":
            case "877":
            case "878":
            case "879":
                flagRes = R.drawable.nl;
                country = "Netherlands";
                break;
            case "880":
                flagRes = R.drawable.kr;
                country = "South Korea";
                break;
            case "884":
                flagRes = R.drawable.kh;
                country = "Cambodia";
                break;
            case "885":
                flagRes = R.drawable.th;
                country = "Thailand";
                break;
            case "888":
                flagRes = R.drawable.sg;
                country = "Singapore";
                break;
            case "890":
                flagRes = R.drawable.in;
                country = "India";
                break;
            case "893":
                flagRes = R.drawable.vn;
                country = "Vietnam";
                break;
            case "896":
                flagRes = R.drawable.pk;
                country = "Pakistan";
                break;
            case "899":
                flagRes = R.drawable.id;
                country = "Polska";
                break;
            case "955":
                flagRes = R.drawable.my;
                country = "Malaisie";
                break;
            case "958":
                flagRes = R.drawable.mkk;
                country = "";
                break;
            case "900":
            case "901":
            case "902":
            case "903":
            case "904":
            case "905":
            case "906":
            case "907":
            case "908":
            case "909":
            case "910":
            case "911":
            case "912":
            case "913":
            case "914":
            case "915":
            case "916":
            case "917":
            case "918":
            case "919":
                flagRes = R.drawable.at;
                break;
            case "930":
            case "931":
            case "932":
            case "933":
            case "934":
            case "935":
            case "936":
            case "937":
            case "938":
            case "939":
                flagRes = R.drawable.au;
                country = "Australia";
                break;
            case "940":
            case "941":
            case "942":
            case "943":
            case "944":
            case "945":
            case "946":
            case "947":
            case "948":
            case "949":
                flagRes = R.drawable.nz;
                country = "New Zealand";
                break;
        }


    }

    public boolean isCountryValid(){
        return !TextUtils.isEmpty(country);
    }

    public boolean isFlagValid(){
        return flagRes != -1;
    }

    public boolean isValid(){
        return isCountryValid() && isFlagValid();
    }

    //---------------------------- GETTERS

    public String getBarcode() {
        return barcode;
    }

    public int getFlagRes() {
        return flagRes;
    }

    public String getCountry() {
        return country;
    }

    public String getFirstThree() {
        return firstThree;
    }
}
