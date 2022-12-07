# script para rodar testes com vegeta

# total de 500 requisições em 5s com PlatformThreads
echo "inicio teste 1>" $(date)
vegeta attack -duration=5s -rate=100 -targets=1-Target_with_Platform_Threads.txt | vegeta report > ./results/execucao-1/results-test-1.txt
echo "fim teste 1>" $(date)

sleep 120s
# total de 500 requisições em 5s com SingleThread
echo "inicio teste 2>" $(date)
vegeta attack -duration=5s -rate=100 -targets=2-Target_with_Single_Thread.txt | vegeta report > ./results/execucao-1/results-test-2.txt
echo "fim teste 2>" $(date)

sleep 120s
# total de 500 requisições em 5s com CachedThreads
echo "inicio teste 3>" $(date)
vegeta attack -duration=5s -rate=100 -targets=3-Target_with_Cached_Threads.txt | vegeta report > ./results/execucao-1/results-test-3.txt
echo "fim teste 3>" $(date)

sleep 120s
# total de 500 requisições em 5s com VirtualThreads
echo "inicio teste 4>" $(date)
vegeta attack -duration=5s -rate=100 -targets=4-Target_with_Virtual_Threads.txt | vegeta report > ./results/execucao-1/results-test-4.txt
echo "fim teste 4>" $(date)

sleep 120s
# total de 500 requisições em 5s com CachedThread e com delay
echo "inicio teste 5>" $(date)
vegeta attack -duration=5s -rate=100 -targets=5-Target_with_Cached_Threads_Delay.txt | vegeta report > ./results/execucao-1/results-test-5.txt
echo "fim teste 5>" $(date)

sleep 120s
# total de 500 requisições em 5s com VirtualThreads e com delay
echo "inicio teste 6>" $(date)
vegeta attack -duration=5s -rate=100 -targets=6-Target_with_Virtual_Threads_Delay.txt | vegeta report > ./results/execucao-1/results-test-6.txt
echo "fim teste 6>" $(date)


echo ">>>>>>>>>> fim <<<<<<<<<<"
