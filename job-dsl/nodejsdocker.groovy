job('NodeJS Docker example') {
    scm {
        git('https://github.com/it-chhaya/express-myapp.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('it.chhaya')
            node / gitConfigEmail('it.chhaya@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('chanchhaya/jenkinswithnodejs')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}