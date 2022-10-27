#include "accessmaterial.hpp"

int AccessMaterial::setActionFilePath(string path){
    ActionFilePath = path;
    return 0;
}

int AccessMaterial::setMaterialDirPath(string path){
    MaterialDirPath = path;
    return 0;
}


int AccessMaterial::addMaterial(string key, Equipment mat){
    ifstream read;
    ofstream write;
    stringstream tmp; tmp << AccessMaterial::MaterialDirPath << key << ".csv";
    string path = tmp.str();

    string line;
    vector<Equipment> Index;

    read.open(path);
    if(read.fail()){
        //Key not Exist; create file:
        write.open(path);
        write << mat;
        write.close();
        read.close();
        return 0;
    }

    int h = 0;
    while(getline(read, line)){
        Index.push_back(Equipment(line));
        h++;
    }

    for(int i=0; i<Index.size(); i++){
        if(Index[i] == mat){
            // Erreur, Label déjà existant
            read.close();
            write.close();
            return -1;
        }
    }

    write.open(path, ios_base::app);
    write << mat;
    write.close();
    read.close();
    return 0;
}

int AccessMaterial::modifyMaterial(string key, int id, Equipment mat){
    return -1;
}

int AccessMaterial::removeMaterial(string key, int id){
    return -1;
}



int AccessMaterial::addAction(Commande cmd){

    //Check si Materiel existe:
    //Si non return -3;

    //Check si Actions possible (Date)
    //sinon return -4;


    ifstream read;
    ofstream write;

    string line;
    vector<Commande> list;
    
    int idfound = -1;

    read.open(ActionFilePath);
    
    if(read.fail()){
        if(cmd.getAction() == 1){
            //cerr << "Actions File not found so created" << endl;
            write.open(ActionFilePath);
            write << cmd << endl;
            write.close();
            read.close();
            return cmd.getId();
        }
        else{
            //cerr << "Action ne peut être effectué, car le produit n'est pas encore commandée" << endl;
            read.close();
            return -2;
        }
    }

    int i = 0;
    while(getline(read,line)){
        list.push_back(Commande(line));
        if(list[i].getId() == -1) continue;

        if(cmd == list[i]){
            //cerr << "action Already Exist" << endl;
            read.close();
            return -1;
        }
        i++;
    }

    int ind = -1;
    bool found = false;
    for(int i = 0; i<list.size() ; i++){
        if(list[i].getId() == -1) continue;

        if(cmd.getEquiId().compare(list[i].getEquiId()) == 0 && cmd.getEquiKey().compare(list[i].getEquiKey()) == 0 ){
            found == true;
            ind = i;
            break;
        }
    }

    if(found == false && cmd.getAction() == 1){
        write.open(ActionFilePath, ios::app);
        write << cmd << endl;
        write.close();
        read.close();
        return cmd.getId();
    }

  
    if(list[ind].getAction() != 1){
        //cerr << "Action ne peut être effectué, car le produit n'est pas encore commandée" << endl;
        read.close();
        return -2;
    }

    write.open(ActionFilePath, ios::app);
    write << cmd << endl;
    write.close();
    read.close();
    return cmd.getId();    
}
        
int AccessMaterial::modifyAction(int id, Commande cmd){
    return -1;
}

int AccessMaterial::removeAction(int id){
    ifstream read;
    string line;
    vector<Commande> vec;

    read.open(AccessMaterial::ActionFilePath);
    if(read.fail()){
        read.close();
        //NotFound
        return -1;
    }

    bool found = false;
    int ind;
    while(getline(read, line)){
        vec.push_back(Commande(line));
    }

    for(int i =0;i<vec.size() ; i++){
        if(vec[i].getId() == id){
            found = true;
            ind = i;
            break;
        }
    }
    if(found == true){
        ofstream write;
        write.open(AccessMaterial::ActionFilePath);
        Commande tmp = vec[ind];
        tmp.setId(-1);
        vec[ind] = tmp;

        for(int i = 0; i<vec.size() ; i++){
            write << vec[i];
        }
        read.close();
        write.close();
        return 0;
    }

    //NotFound
    return -1;
}

Commande AccessMaterial::getAction(int id){
    ifstream read;
    string line;
    vector<Commande> vec;

    read.open(AccessMaterial::ActionFilePath);

    bool found = false;
    int ind;
    while(getline(read, line)){
        vec.push_back(Commande(line));
    }

    for(int i =0;i<vec.size() ; i++){
        if(vec[i].getId() == id){
            found = true;
            ind = i;
            break;
        }
    }
    if(found == true){
        return vec[ind];
    }

    throw "Id Not Found";
}

vector<Commande> AccessMaterial::getAllActions(){
    ifstream read;
    string line;
    vector<Commande> vec;

    read.open(AccessMaterial::ActionFilePath);

    while(getline(read, line)){
        Commande tmp = Commande(line);
        if(tmp.getId() == -1) continue;
        vec.push_back(tmp);
    }
    cerr<<"Action File lines: " << vec.size()<<endl;
    return vec;
}