#include <vector>
#include <iostream>

using namespace std;

vector<int> c1firstpos;
vector<int> c1lastpos;
vector<int> c2firstpos;
vector<int> c2lastpos;
vector<int> nodefirstpos;
vector<int> nodelastpos;
bool c1null, c2null;

int n1, n2, val;

int main()
{
    char node;
    cout << "Enter node: ";
    cin >> node;
    if (node == '*')
    {
        cout << "Total child last pos: ";
        cin >> n2;
        cout << "Enter child pos: ";
        for (int i = 0; i < n2; i++)
        {
            cin >> val;
            c1lastpos.push_back(val);
        }
        cout << "node * last pos: ";
        for (int i = 0; i < n2; i++)
        {
            cout << c1lastpos.at(i) << " ";
        }
    }
    else if (node == '|')
    {
        cout << "enter number of c1 last pos: ";
        cin >> n1;
        cout << "enter last pos of c1: ";
        for (int i = 0; i < n1; i++)
        {
            cin >> val;
            c1lastpos.push_back(val);
        }
        cout << "enter number of c2 last post: ";
        cin >> n2;
        cout << "enter last pos of c2: ";

        for (int i = 0; i < n2; i++)
        {
            cin >> val;
            c2lastpos.push_back(val);
        }
        for (int i = 0; i < n1; i++)
        {
            nodelastpos.push_back(c1lastpos.at(i));
        }
        for (int i = 0; i < n2; i++)
        {
            nodelastpos.push_back(c2lastpos.at(i));
        }
        cout << "Last pos of current node: ";
        for (int i = 0; i < nodelastpos.size(); i++)
        {
            cout << nodelastpos.at(i) << " ";
        }
    }
    else if (node == '.')
    {
        cout << "Is C2 nullable? 0(False)/1(True): ";
        cin >> c2null;
        if (c2null)
        {
            cout << "Number of c1 last pos: ";
            cin >> n1;
            cout << "Enter c1 last pos: ";
            for (int i = 0; i < n1; i++)
            {
                cin >> val;
                c1lastpos.push_back(val);
            }
        }
        cout << "Number of c2 last pos: ";
        cin >> n2;
        cout << "Enter c2 last pos: ";
        for (int i = 0; i < n2; i++)
        {
            cin >> val;
            c2lastpos.push_back(val);
        }

        cout << "Current node last pos: ";
        if (c2null)
        {
            for (int i = 0; i < n1; i++)
            {
                cout << c1lastpos.at(i) << " ";
            }
        }
        for (int i = 0; i < n2; i++)
        {
            cout << c2lastpos.at(i) << " ";
        }
    }
}