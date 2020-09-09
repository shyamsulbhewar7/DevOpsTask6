job("Job for GitHub") {
  description("Pulls Codes and scripts From GitHub repository and copies them into a folder in base system.")

  scm {
    github("shyamwin/DevOpsTask6", "master")
  }

  triggers {
    scm("* * * * *")
  }

  steps {
    shell('''
    sudo cp -rvf * /home/jenkins_groovy
    ''')
  }
}

job("Job for Deployment") {
  description("Checks the language of code and deploys it on respective interpreter image.")

  triggers {
    upstream("Job for GitHub")
  }

  steps {
    shell(readFileFromWorkspace("deploy.sh"))
  }
}

job("Testing job") {
  description("Tests whether the page has been deployed or not.")

  triggers {
    upstream("Job for Deployment")
  }

  steps {
    shell(readFileFromWorkspace("test_app.sh"))
  }
}

job("Mailing Job") {
  description("If site is not working, it will mail the developer. ")

  triggers {
    upstream("Testing job","FAILURE")
  }

  publishers {
        mailer('thebrucebanner007@gmail.com')
  }
}

buildPipelineView("Deploy_groovy") {
  title("Deploy webpage using Jenkins(auomated by groovy) and K8s")
  selectedJob("Job for Github")
  displayedBuilds(1)
  refreshFrequency(4)
  alwaysAllowManualTrigger()
  showPipelineParameters()
}
