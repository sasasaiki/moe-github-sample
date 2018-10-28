package app.saiki


import apple.NSObject
import apple.foundation.NSArray
import apple.foundation.NSBundle
import apple.foundation.NSCoder
import apple.foundation.NSIndexPath
import apple.foundation.NSMethodSignature
import apple.foundation.NSOperationQueue
import apple.foundation.NSSet
import apple.foundation.enums.NSQualityOfService
import apple.uikit.UITableView
import apple.uikit.UITableViewCell
import apple.uikit.UIViewController
import apple.uikit.protocol.UITableViewDataSource
import apple.uikit.protocol.UITableViewDelegate
import io.reactivex.ios.schedulers.IOSSchedulers
import io.reactivex.schedulers.Schedulers
import saiki.app.common.GitHubRepo
import saiki.app.common.RepositoryListContract
import saiki.app.common.RepositoryListPresenterImpl

import org.moe.natj.c.ann.FunctionPtr
import org.moe.natj.general.NatJ
import org.moe.natj.general.Pointer
import org.moe.natj.general.ann.Generated
import org.moe.natj.general.ann.Mapped
import org.moe.natj.general.ann.MappedReturn
import org.moe.natj.general.ann.NInt
import org.moe.natj.general.ann.NUInt
import org.moe.natj.general.ann.Owned
import org.moe.natj.general.ann.RegisterOnStartup
import org.moe.natj.general.ann.Runtime
import org.moe.natj.general.ptr.VoidPtr
import org.moe.natj.objc.Class
import org.moe.natj.objc.ObjCRuntime
import org.moe.natj.objc.SEL
import org.moe.natj.objc.ann.ObjCClassName
import org.moe.natj.objc.ann.Selector
import org.moe.natj.objc.map.ObjCObjectMapper

import java.util.ArrayList

@Runtime(ObjCRuntime::class)
@ObjCClassName("RepositoryListViewController")
@RegisterOnStartup
class RepositoryListViewController private constructor(peer: Pointer) : UIViewController(peer), RepositoryListContract.View, UITableViewDataSource, UITableViewDelegate {

    private val operationQueue: NSOperationQueue = NSOperationQueue.alloc().init()

    private var repoList: List<GitHubRepo> = ArrayList()

    override fun tableViewCellForRowAtIndexPath(tableView: UITableView, indexPath: NSIndexPath): UITableViewCell {
        val cell = tableView.dequeueReusableCellWithIdentifierForIndexPath(CELL_IDENTIFIER, indexPath) as UITableViewCell
        cell.textLabel().setText(repoList[indexPath.row().toInt()].name)
        return cell
    }

    override fun tableViewNumberOfRowsInSection(tableView: UITableView, @NInt section: Long): Long {
        return repoList.size.toLong()
    }

    init {
        operationQueue.setQualityOfService(NSQualityOfService.Background)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()
        val repositoryPresenter = RepositoryListPresenterImpl(this)
        repositoryPresenter.getRepositories(Schedulers.io(), IOSSchedulers.mainThread())
    }

    override fun showRepoList(repoList: List<GitHubRepo>) {
        this.repoList = repoList
        repositoryTableView()!!.reloadData()
    }

    @Generated
    @Selector("init")
    external override fun init(): RepositoryListViewController

    @Generated
    @Selector("initWithCoder:")
    external override fun initWithCoder(aDecoder: NSCoder): RepositoryListViewController

    @Generated
    @Selector("initWithNibName:bundle:")
    external override fun initWithNibNameBundle(
            nibNameOrNil: String, nibBundleOrNil: NSBundle): RepositoryListViewController

    @Generated
    @Selector("repositoryTableView")
    external fun repositoryTableView(): UITableView?

    @Generated
    @Selector("setRepositoryTableView:")
    external fun setRepositoryTableView_unsafe(value: UITableView?)

    @Generated
    fun setRepositoryTableView(value: UITableView?) {
        val __old = repositoryTableView()
        if (value != null) {
            org.moe.natj.objc.ObjCRuntime.associateObjCObject(this, value)
        }
        setRepositoryTableView_unsafe(value)
        if (__old != null) {
            org.moe.natj.objc.ObjCRuntime.dissociateObjCObject(this, __old)
        }
    }

    companion object {

        private val CELL_IDENTIFIER = "repositoryItemCell"


        init {
            NatJ.register()
        }

        @Generated
        @Selector("accessInstanceVariablesDirectly")
        external fun accessInstanceVariablesDirectly(): Boolean

        @Generated
        @Owned
        @Selector("alloc")
        external fun alloc(): RepositoryListViewController

        @Generated
        @Selector("allocWithZone:")
        @MappedReturn(ObjCObjectMapper::class)
        external fun allocWithZone(zone: VoidPtr): Any

        @Generated
        @Selector("attemptRotationToDeviceOrientation")
        external fun attemptRotationToDeviceOrientation()

        @Generated
        @Selector("automaticallyNotifiesObserversForKey:")
        external fun automaticallyNotifiesObserversForKey(key: String): Boolean

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:")
        external fun cancelPreviousPerformRequestsWithTarget(
                @Mapped(ObjCObjectMapper::class) aTarget: Any)

        @Generated
        @Selector("cancelPreviousPerformRequestsWithTarget:selector:object:")
        external fun cancelPreviousPerformRequestsWithTargetSelectorObject(
                @Mapped(ObjCObjectMapper::class) aTarget: Any, aSelector: SEL,
                @Mapped(ObjCObjectMapper::class) anArgument: Any)

        @Generated
        @Selector("classFallbacksForKeyedArchiver")
        external fun classFallbacksForKeyedArchiver(): NSArray<String>

        @Generated
        @Selector("classForKeyedUnarchiver")
        external fun classForKeyedUnarchiver(): Class

        @Generated
        @Selector("clearTextInputContextIdentifier:")
        external fun clearTextInputContextIdentifier(identifier: String)

        @Generated
        @Selector("debugDescription")
        external fun debugDescription_static(): String

        @Generated
        @Selector("description")
        external fun description_static(): String

        @Generated
        @Selector("hash")
        @NUInt
        external fun hash_static(): Long

        @Generated
        @Selector("instanceMethodForSelector:")
        @FunctionPtr(name = "call_instanceMethodForSelector_ret")
        external fun instanceMethodForSelector(
                aSelector: SEL): NSObject.Function_instanceMethodForSelector_ret

        @Generated
        @Selector("instanceMethodSignatureForSelector:")
        external fun instanceMethodSignatureForSelector(
                aSelector: SEL): NSMethodSignature

        @Generated
        @Selector("instancesRespondToSelector:")
        external fun instancesRespondToSelector(aSelector: SEL): Boolean

        @Generated
        @Selector("isSubclassOfClass:")
        external fun isSubclassOfClass(aClass: Class): Boolean

        @Generated
        @Selector("keyPathsForValuesAffectingValueForKey:")
        external fun keyPathsForValuesAffectingValueForKey(
                key: String): NSSet<String>

        @Generated
        @Owned
        @Selector("new")
        @MappedReturn(ObjCObjectMapper::class)
        external fun new_objc(): Any

        @Generated
        @Selector("resolveClassMethod:")
        external fun resolveClassMethod(sel: SEL): Boolean

        @Generated
        @Selector("resolveInstanceMethod:")
        external fun resolveInstanceMethod(sel: SEL): Boolean

        @Generated
        @Selector("setVersion:")
        external fun setVersion(@NInt aVersion: Long)

        @Generated
        @Selector("superclass")
        external fun superclass_static(): Class

        @Generated
        @Selector("version")
        @NInt
        external fun version_static(): Long
    }


}
