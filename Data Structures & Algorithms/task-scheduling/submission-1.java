class Solution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> taskFrequency = new HashMap<>();
        for (char c : tasks) {
            int frequency = taskFrequency.getOrDefault(c, 0);
            taskFrequency.put(c, frequency + 1);
        }
        
        Queue<Task> pendingTasks = new LinkedList<>();
        PriorityQueue<Task> readyTasks = new PriorityQueue<>();
        for (var entry : taskFrequency.entrySet()) {
            var task = new Task(0, entry.getValue(), entry.getKey());
            // System.out.println(task);
            pendingTasks.offer(task);
        }

        int time = 0;
        while (pendingTasks.size() > 0 || readyTasks.size() > 0) {
            //System.out.println("time: " + time);
            if (readyTasks.isEmpty() 
                && time < pendingTasks.peek().availableAt) {
                //System.out.println("No task to execute at " + time);
                time++;
                continue;
            }
            while (pendingTasks.size() > 0 
                && time >= pendingTasks.peek().availableAt) {
                readyTasks.offer(pendingTasks.poll());
            }
            var task = readyTasks.poll();
            task.pendingRuns--;
            time++;
            if (task.pendingRuns > 0) {
                task.availableAt = time + n;
                //System.out.printf("executedTasks: %s\n", task.toString());
                pendingTasks.offer(task);
            } else {
                //System.out.printf("executedTasks: %s\n", task.toString());
            }
            
        }

        return time;
    }

    
}

class Task implements Comparable {
    int availableAt;
    int pendingRuns;
    char name;

    public Task (int availableAt, int pendingRuns, char name) {
        this.availableAt = availableAt;
        this.pendingRuns = pendingRuns;
        this.name = name;
    }

    public int compareTo(Object o) {
        Task a = this;
        Task b = (Task) o;
        return Integer.compare(b.pendingRuns, a.pendingRuns);
    }

    public String toString() {
        return String.format("{availableAt: %d, pendingRuns: %d, name: %c}", 
            availableAt, pendingRuns, name);
    }
}
