#include <iostream>
using namespace std;

/**
     * we have n jobs sorted with time/importance from big to small
     * that we need to decide in 1|h(1),N - res|sum wjCj which to do before the blocking time interval and which after
     *
     * @param p array size n of the time that each job need
     * @param w array size n of the relative importance of each job
     * @param len the number of objects
     * @param s the begin of the time which the machine cannot perform the processing of any job
     * @param t the end of the time which the machine cannot perform the processing of any job
     *
     * @return array size n that present which job to do before s with 1 and which after t with 0
     */
static int* N_RESProblem(unsigned int* p, unsigned int* w,const int len, const unsigned int s, const unsigned int t,
                         unsigned int& min){
     unsigned int Mat[len+1][s + 1];
     for (int i = 0; i < len + 1; i++) {
         for (int j = 0; j < s + 1; j++) {
             Mat[i][j] = -1;
         }
     }
     Mat[0][0] = 0;
     for (int i = 0; i < len; i++) Mat[0][0] += p[i]*w[i];
     unsigned int Ak=0;//The sum of all the weights we have passed
     for(int i = 0;i < len; i++) {
         for (int Yk = 0; Yk < s + 1; Yk++) {
             if(Mat[i][Yk] != -1){
                 /////////don't add
                 unsigned int dontAdd = Mat[i][Yk] + w[i]*(Ak - Yk) + w[i]*t;
                 Mat[i + 1][Yk] = (Mat[i + 1][Yk] == -1) ? dontAdd : (dontAdd < Mat[i + 1][Yk] ? dontAdd : Mat[i + 1][Yk]);

                 /////////add
                 if(Yk + p[i+1 -1] <= s){
                     unsigned int add = Mat[i][Yk] + w[i + 1 -1]*Yk;
                     Mat[i + 1][Yk + p[i]] = (Mat[i + 1][Yk + p[i]] == -1) ? add : (add < Mat[i + 1][Yk + p[i]] ? add : Mat[i + 1][Yk + p[i]]);
                 }
             }
         }
         Ak+=p[i];
     }
     int * x = new int[len];
     min = Mat[len - 1][0];
     int minIndex = 0;
     for (int i = 1; i < s + 1; i++) {
         if(min > Mat[len][i] && Mat[len][i] != -1){
             min =  Mat[len][i];
             minIndex = i;
         }
     }
     for(int i = len - 1; i >= 0; i--){
         Ak -= p[i];
         if(Mat[i][minIndex] + w[i]*(Ak - minIndex) + w[i]*t == Mat[i + 1][minIndex] && Mat[i][minIndex] != -1){
             x[i] = 0;
         }
         else{
             x[i] = 1;
             minIndex -= p[i];
         }
     }
     return x;
 }

void printSolution(unsigned int* p,const int len, const unsigned int t,
                   unsigned int min,  int* x){
    unsigned int endTime = 0;
    cout<<"-------------------------\n";
    cout<<"befor stoping\n";
    cout<<"-------------------------\n";
    for (int i = 0; i < len; ++i) {
        if(x[i]) {
            endTime += p[i];
            cout << "job number: " << i << "  its end time is: " << endTime<<"\n";
        }
    }
    endTime = t;
    cout<<"-------------------------\n";
    cout<<"after stoping\n";
    cout<<"-------------------------\n";
    for (int i = 0; i < len; ++i) {
        if(!x[i]) {
            endTime += p[i];
            cout << "job number: " << i << " its end time is: " << endTime<<"\n";
        }
    }
    cout << "\nthe Cost function is:  "<<min<<"\n";

}
int main(){
    unsigned int min;
    int _n = 2;
    unsigned int _p[] = {2,1};
    unsigned int _w[] = {2,1};
    unsigned int _t=5;
    unsigned int _s=2;
    int * _x = N_RESProblem(_p, _w, _n, _s, _t, min);
    printSolution(_p, _n, _t, min, _x);
    delete [] _x;
    int n = 20;
    unsigned int p[] = {6, 9, 8, 12, 7, 10, 6, 14, 24, 35, 32, 10, 20, 8, 19, 36, 38, 45, 12, 32};
    unsigned int w[] = {22, 24, 21, 21, 12, 16, 9, 10, 17, 21, 17, 5, 10, 4, 9, 16, 12, 14, 2, 3};
    unsigned int s = 76;
    unsigned int t = 153 + 76;
    int* x = N_RESProblem(p, w, n, s, t, min);
    printSolution(p, n, t, min, x);
    delete [] x;

}