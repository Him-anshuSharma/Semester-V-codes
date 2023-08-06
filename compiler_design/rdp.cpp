#include<iostream>
#include<string>

using namespace std;

bool E();
bool T();
bool EP();
bool F();
bool TP();

string input;
int pos = 0;

bool match(char ch){
    if(input[pos] == ch){
        pos++;
        return true;
    }
    return false;
}

bool E(){
    if(T()){
        if(EP()){
            return true;
        }
        return false;
    }
    return false;
}

bool EP(){
    if(match('+')){
        if(T()){
            if(EP()){
                return true;
            }
            return false;
        }
        return false;
    }
    return true;
}

bool T(){
    if(F()){
        if(TP()){
            return true;
        }
        return false;
    }
    return false;
}

bool TP(){
    if(match('*')){
        if(F()){
            if(TP()){
                return true;
            }
            return false;
        }
        return false;
    }
    return true;
}

bool F(){
    if(match('i')){
        if(match('d')){
            return true;
        }
    }
    else if(match('(')){
        if(E()){
            if(match(')')){
                return true;
            }
            return false;
        }
        return false;
    }
    return false;
}

int main(){
    cin >> input;
    if(E() && input.length() == pos){
        cout << "Success";
        return 0;
    }
    cout << "Failed";
    return 0;
}