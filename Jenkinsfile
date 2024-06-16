pipeline {
	agent any
	stages {
		// stage('Hello World') {
		// 	steps {
		// 		echo 'Hello World'
		// 	}
		// }
		stage('Get ansible playbooks'){
			steps {
				build job: 'ansible'
			}
		}

		stage('Get ansible roles'){
			steps {
				sh '''
					ansible-galaxy role install geerlingguy.mysql
				'''
			}
		}

		stage('Playbook mysql.yaml') {
			steps {
				sh '''
					ansible-playbook -i ~/workspace/ansible/hosts.yaml -l backend_hosts ~/workspace/ansible/playbooks/mysql.yaml
				'''
			}
		}
	}
}