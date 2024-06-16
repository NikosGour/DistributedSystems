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

		stage('Playbook ping.yaml') {
			steps {
				sh '''
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml ~/workspace/ansible/playbooks/ping.yaml
				'''
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
					ansible-playbook -i ~/workspace/ansible/hosts.yaml ~/workspace/ansible/playbooks/mysql.yaml
				'''
			}
		}

		stage('Playbook spring.yaml') {
			steps {
				sh '''
					ansible-playbook -i ~/workspace/ansible/hosts.yaml ~/workspace/ansible/playbooks/spring.yaml
				'''
			}
		}

		stage('Playbook react.yaml') {
			steps {
				sh '''
					ansible-playbook -i ~/workspace/ansible/hosts.yaml ~/workspace/ansible/playbooks/react.yaml
				'''
			}
		}
	}
}