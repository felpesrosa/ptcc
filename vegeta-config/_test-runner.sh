# script para rodar testes com vegeta

# total de 500 requisições em 5s com PlatformThreads
echo ">> rodando teste 1"
vegeta attack -duration=5s -rate=100 -targets=1-Target_with_Platform_Threads.txt | vegeta report > ./results/results-test-1.txt
echo "fim teste 1"

sleep 120s
# total de 500 requisições em 5s com SingleThread
echo ">> rodando teste 2"
vegeta attack -duration=5s -rate=100 -targets=2-Target_with_Single_Thread.txt | vegeta report > ./results/results-test-2.txt
echo "fim teste 2"

sleep 120s
# total de 500 requisições em 5s com CachedThreads
echo ">> rodando teste 3"
vegeta attack -duration=5s -rate=100 -targets=3-Target_with_Cached_Threads.txt | vegeta report > ./results/results-test-3.txt
echo "fim teste 3"

sleep 120s
# total de 500 requisições em 5s com VirtualThreads
echo ">> rodando teste 4"
vegeta attack -duration=5s -rate=100 -targets=4-Target_with_Virtual_Threads.txt | vegeta report > ./results/results-test-4.txt
echo "fim teste 4"

sleep 120s
# total de 500 requisições em 5s com CachedThread e com delay
echo ">> rodando teste 5"
vegeta attack -duration=5s -rate=100 -targets=5-Target_with_Cached_Threads_Delay.txt | vegeta report > ./results/results-test-5.txt
echo "fim teste 5"

sleep 120s
# total de 500 requisições em 5s com VirtualThreads e com delay
echo ">> rodando teste 6"
vegeta attack -duration=5s -rate=100 -targets=6-Target_with_Virtual_Threads_Delay.txt | vegeta report > ./results/results-test-6.txt
echo "fim teste 6"


echo ">>>>>>>>>> fim <<<<<<<<<<"
